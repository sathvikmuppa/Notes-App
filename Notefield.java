import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Notefield extends JPanel{

    private static JTextArea text;

    Notefield() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.blue);

        this.setMinimumSize(new Dimension(100,400));

        text = new JTextArea();
        text.setMargin(new Insets(5, 5, 5, 5));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        setText(Sidebar.getText(Sidebar.getRecent()));
        Sidebar.setButton(0);

        JScrollPane scroll = new JScrollPane(text);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  

        this.add(scroll, BorderLayout.CENTER);
    }

    public static void setText(String s) {
        text.setText(s);
        text.setVisible(true);
        text.setCaretPosition(0);
    } 
}
