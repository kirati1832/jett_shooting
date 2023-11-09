import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.lang.Math;
public class jett_enemy {
    EnemyProjectile Enemyball;
    URL imagejett_em = this.getClass().getResource("jett_enemy.png");
    Image imagejetem = new ImageIcon(imagejett_em).getImage();
    int health = 3;
    int x = 1000;
    int y = 200;
    int max_y = 880;
    int min_y = 120;
    int range_y = max_y - min_y + 1;
    int max_rad = 300;
    int min_rad = 0;
    int range_rad = max_rad - min_rad + 1;
    int rad = 0;
    boolean isAlive = true;
    jett_enemy(){
        randy();
        Enemyball = new EnemyProjectile(x,y,randrad());
    }

    public void hit() {
        health--;
        if (health <= 0) {
            isAlive = false;
        }
    }
    public void respawn() {
        health = 3;
        isAlive = true;
        randy();
    }
    public int randrad(){
        while (true){
            rad = (int)(Math.random() * range_rad) + min_rad;
            if (rad >= 120&&rad<=240){
                return rad;
            }
        }
    }
    public void randy(){
        y = (int)(Math.random() * range_y) + min_y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 120, 100);
    }



}


