import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class Window extends JFrame {
    Window() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Notes");
        this.setSize(700, 450);
        this.setLayout(new BorderLayout());
        //this.setUndecorated(true);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new Sidebar(), new Notefield());
        split.setOneTouchExpandable(true);
        //split.setDividerSize(2);

        this.add(new Toolbar(), BorderLayout.NORTH);
        this.add(split, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
