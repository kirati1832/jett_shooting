import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Background {
    URL imagebg = this.getClass().getResource("background1.jpg");
    Image imagebg1 = new ImageIcon(imagebg).getImage();
    public int x_bg = 0;
    public int y_bg = 0;
    public void Backgroundack(int x,int y){
        x_bg = x;
        y_bg = y;
    }
}
