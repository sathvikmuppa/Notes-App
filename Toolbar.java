import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class Toolbar extends JPanel{
    Toolbar() {
        this.setPreferredSize(new Dimension(400, 27));
        this.setLayout(new BorderLayout());

        JToolBar tb = new JToolBar();
        tb.setFloatable(false);

        JPanel side = new JPanel();
        side.setLayout(new GridLayout(1, 2, 5, 0));
        JPanel text = new JPanel();
        text.setLayout(new GridLayout(1, 4, 5, 0));
        
        JButton newButton = new JButton("New");
        JButton delButton = new JButton("Delete");
        side.add(newButton);
        side.add(delButton);

        JComboBox<String> fontButton = new JComboBox<>(new String[]{"Font", "Calilbri", "Times", "Ariel"});
        text.add(fontButton);

        JButton cbButton = new JButton("Checklist");
        JButton tableButton = new JButton("Table");
        JButton imageButton = new JButton("Image");
        text.add(cbButton);
        text.add(tableButton);
        text.add(imageButton);

        this.add(side, BorderLayout.WEST);
        this.add(text, BorderLayout.EAST);

    }
}
