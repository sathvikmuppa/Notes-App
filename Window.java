import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class Window extends JFrame {
    Window() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Notes");
        this.setSize(700, 450);
        this.setLayout(new BorderLayout());
        //this.setUndecorated(true);

        this.add(new Toolbar(), BorderLayout.NORTH);
        this.add(new Sidebar(), BorderLayout.WEST);
        this.add(new Notefield(), BorderLayout.CENTER);

        this.setVisible(true);
    }
}
