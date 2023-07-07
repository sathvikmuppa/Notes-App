import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class Window extends JFrame {
    Window() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Notes");
        this.setSize(750, 450);
        this.setMinimumSize(new Dimension(625, 200));
        this.setLayout(new BorderLayout());

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new Sidebar(), new Notefield());
        split.setOneTouchExpandable(true);

        this.add(new Toolbar(), BorderLayout.NORTH);
        this.add(split, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
