import java.awt.*;

public class EnemyProjectile {
    int x, y;
    double angle;
    int speed = 5;
    boolean active = true;

    public EnemyProjectile(int x, int y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = Math.toRadians(angle);
    }

    public void move() {
        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillOval(x, y, 10, 10);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);
    }
}
