import java.awt.event.*;

class move implements KeyListener {
    public boolean w_k, a_k, s_k, d_k,space_k,esc_k;

    @Override
    public void keyTyped(KeyEvent e) {

    }
        public void block_movement() {
        w_k = false;
        a_k = false;
        s_k = false;
        d_k = false;
        space_k = false;
        esc_k = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case (KeyEvent.VK_W) -> w_k = true;
            case (KeyEvent.VK_S) -> s_k = true;
            case (KeyEvent.VK_A) -> a_k = true;
            case (KeyEvent.VK_D) -> d_k = true;
            case (KeyEvent.VK_SPACE) -> space_k = true;
            case (KeyEvent.VK_ESCAPE) -> esc_k = true;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            w_k = false;
        }
        if (code == KeyEvent.VK_S) {
            s_k = false;
        }
        if (code == KeyEvent.VK_A) {
            a_k = false;
        }
        if (code == KeyEvent.VK_D) {
            d_k = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            space_k = false;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            esc_k = false;
            System.out.println("esc");
        }
    }
}
