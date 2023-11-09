import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class jett {
    move m;
    jett(move m1){
        m = m1;
    }
    URL imagejet = this.getClass().getResource("jett_me.png");
    Image imagejet1 = new ImageIcon(imagejet).getImage();
    URL heartURL = this.getClass().getResource("heart.png");
    Image heartImage = new ImageIcon(heartURL).getImage();
    int x_jett = 20, y_jett = 500;
    int health = 3;


    public void drawHealth(Graphics g) {
        for (int i = 0; i < health; i++) {
            g.drawImage(heartImage, 10 + (i * 55), 10, 50, 50, null);
        }
    }

    public void hit() {
        health--;
    }
    public void healthup(){
        health++;
    }
    public Rectangle getBounds() {
        return new Rectangle(x_jett, y_jett, 120, 100);
    }


}


