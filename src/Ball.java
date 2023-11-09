import javax.swing.*;
import java.awt.*;

public class Ball extends JPanel {
    int x_ball = 0;
    int y_ball = 0;
    int speedX = 10;
    int speedY = 0;



    public Ball(int x, int y){
        x_ball = x;
        y_ball = y;
    }


    public void moveBall() {
        x_ball += speedX;
        y_ball += speedY;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        g.fillOval(x_ball, y_ball , 10 , 10);
    }
    public Rectangle getBounds() {
        return new Rectangle(x_ball, y_ball, 10, 10);
    }
}
