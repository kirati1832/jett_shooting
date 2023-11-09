import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main extends JFrame {
    Main() {
        add(new DrawArea());
    }

    class DrawArea extends JPanel {

        Background b1 = new Background();
        move m = new move();
        jett_enemy j2 = new jett_enemy();
        jett_enemy2 j3 = new jett_enemy2();
        jett j1 = new jett(m);
        List<Ball> balls = new ArrayList<>();
        double delay = 0;
        boolean ac = false;
        double speed_delay = 0.05;
        int score = 0;
        int lastscore = 0;
        boolean paused = false;

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(b1.imagebg1, b1.x_bg, b1.y_bg, 4200, 1000, null);
            if (j2.isAlive) {
                g.drawImage(j2.imagejetem, j2.x, j2.y ,120, 100, this);
                paintProjectiles(g);
            }
            if(j3.isAlive){
                g.drawImage(j3.imagejetem, j3.x, j3.y ,120, 100, this);
                paintProjectiles2(g);
            }
            j1.drawHealth(g);
            drawScore(g);
            g.drawImage(j1.imagejet1, j1.x_jett, j1.y_jett, 120, 100, this);
            /*g.setColor(new Color(255, 0, 0, 100));
            Rectangle bounds = j1.getBounds(); //checkhitboxjett1
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);*/
            synchronized (balls) {
                for (Ball ball : balls) {
                    ball.paintComponent(g);
                }
            }
            if (j1.health <= 0) {
                gameOver(g);
            }

        }



        private Rectangle restartButtonBounds = new Rectangle(550,550 , 200, 50);

        private void gameOver(Graphics g) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over", getWidth() / 2 - 150, getHeight() / 2 - 100);
            g.drawString("Score: " + score, getWidth() / 2 - 150, getHeight() / 2 );

            // Draw the restart button
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawRect(restartButtonBounds.x, restartButtonBounds.y, restartButtonBounds.width, restartButtonBounds.height);
            g.drawString("Restart Game", restartButtonBounds.x + 25, restartButtonBounds.y + 35);
        }

        private void restartGame() {
            // Reset all game state variables
            score = 0;
            lastscore = 0;
            j1.health = 3;
            balls.clear();
            projectiles.clear();
            j2.isAlive = true;
            j3.isAlive = true;
            j1.x_jett = 20;
            j1.y_jett = 500;
            // You may need to reset other variables too

            // Repaint the game to reflect the reset
            repaint();
        }

        public void launchBall() {
            Ball newBall = new Ball(j1.x_jett + 115, j1.y_jett + 40);
            balls.add(newBall);
        }

        public void update(){
            if(m.a_k) {
                if(j1.x_jett>0) {
                    j1.x_jett -= 6;
                    b1.x_bg += 1;
                }
            }
            if (m.d_k) {
                if(j1.x_jett<getWidth()-120){
                    j1. x_jett+=6;
                    b1.x_bg -= 1;
                }
            }
            if (m.s_k) {
                if (j1.y_jett < getHeight() - 100) {
                    j1.y_jett += 6;
                }
            }
            if (m.w_k) {
                if(j1.y_jett>0) {
                    j1. y_jett -= 6;
                }
            }
            if(Math.abs(b1.x_bg)< getWidth()){
                b1.x_bg -= 2;
            }
            else{
                b1.x_bg = 0;
            }
            if(ac){
                delay += speed_delay;
            }
            if(delay >= 1){
                ac = false;
                delay = 0;
            }
            if(score == 0){

            }
            else if(score%500 == 0 && score != lastscore){
                    j1.healthup();
                    lastscore = score;
            }
            repaint();

        }

        public void updateBall() {
            synchronized (balls) {
                List<Ball> toRemove = new ArrayList<>();
                if (m.space_k && !ac) {
                    launchBall();
                    ac = true;
                }
                updateProjectiles();
                for (EnemyProjectile projectile : projectiles) {
                    if (projectile.getBounds().intersects(j1.getBounds())) {
                        j1.hit();
                        projectile.active = false;
                    }
                }

                for (Ball ball : balls) {
                    ball.moveBall();
                    if (ball.x_ball > getWidth()) {
                        toRemove.add(ball);
                    }
                    if (j2.isAlive && ball.getBounds().intersects(j2.getBounds())) {
                        j2.hit();
                        toRemove.add(ball);
                        if (!j2.isAlive) {
                            score += 100;
                            j2.respawn();
                        }
                    }
                    if (j3.isAlive && ball.getBounds().intersects(j3.getBounds())) {
                        j3.hit();
                        toRemove.add(ball);
                        if (!j3.isAlive) {
                            score += 100;
                            j3.respawn();
                        }
                    }
                }
                projectiles.removeIf(p -> !p.active);
                balls.removeAll(toRemove);
                repaint();
            }
        }

        private void drawScore(Graphics g) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Score: " + score, getWidth() - 220, 50);
        }
        //Enemyballdegrees
        List<EnemyProjectile> projectiles = new LinkedList<>();
        double shootAngle = 120;
        boolean increasingAngle = true;
        public void shoot() {
            projectiles.add(new EnemyProjectile(this.j2.x, this.j2.y+45, shootAngle));
            if (increasingAngle) {
                shootAngle += 10;
                if (shootAngle >= 240) {
                    increasingAngle = false;
                }
            } else {
                shootAngle -= 10;
                if (shootAngle <= 120) {
                    increasingAngle = true;
                }
            }
        }
        public void shoot2() {
            projectiles.add(new EnemyProjectile(this.j3.x, this.j3.y+45, shootAngle));
            if (increasingAngle) {
                shootAngle += 10;
                if (shootAngle >= 240) {
                    increasingAngle = false;
                }
            } else {
                shootAngle -= 10;
                if (shootAngle <= 120) {
                    increasingAngle = true;
                }
            }
        }

        // jettemshooting
        public void updateProjectiles() {
            List<EnemyProjectile> toRemove = new LinkedList<>();
            for (EnemyProjectile projectile : projectiles) {
                projectile.move();
                if (projectile.x < 0 || projectile.x > 1400 || projectile.y < 0 || projectile.y > 1000) {
                    toRemove.add(projectile);
                }
            }
            projectiles.removeAll(toRemove);
        }

        public void paintProjectiles(Graphics g) {
            for (EnemyProjectile projectile : projectiles) {
                projectile.paintComponent(g);
            }
        }
        public void paintProjectiles2(Graphics g) {
            for (EnemyProjectile projectile : projectiles) {
                projectile.paintComponent(g);
            }
        }


        public DrawArea() {
            this.addKeyListener(m);

                Thread movement = new Thread(new Runnable() {
                    public void run() {
                        while (true) {
                            try {
                                update();
                                repaint();
                                Thread.sleep(10);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });

                Thread jett_shoot = new Thread(new Runnable() {
                    public void run() {
                        while (true) {
                            try {
                                updateBall();
                                repaint();
                                Thread.sleep(10);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                Thread jettem_shoot = new Thread(new Runnable() {
                    public void run() {
                        while (true) {
                            try {
                                if (j2.isAlive) {
                                    shoot();
                                }
                                if (j3.isAlive) {
                                    shoot2();
                                }
                                repaint();
                                Thread.sleep(600);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                setFocusable(true);
                movement.start();
                jett_shoot.start();
                jettem_shoot.start();
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (restartButtonBounds.contains(e.getPoint()) && j1.health <= 0) {
                        restartGame();
                    }
                }
            });

            }
        }





    public static void main(String[] args) {
        Main frame = new Main();
        frame.setTitle("Jett Shooting");
        frame.setSize(1400, 1000);
        frame.setMinimumSize(new Dimension(1400, 1000));
        frame.setMaximumSize(new Dimension(1400, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

}