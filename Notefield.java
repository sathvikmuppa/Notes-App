import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Notefield extends JPanel{
    Notefield() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.blue);

        JTextArea text = new JTextArea();
        text.setMargin(new Insets(5, 5, 5, 5));
        JScrollPane scroll = new JScrollPane(text);

        this.add(scroll, BorderLayout.CENTER);
    }
}
