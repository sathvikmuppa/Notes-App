import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener {

    JButton newButton;
    JButton delButton;
    JButton saveButton;
    JComboBox<String> fontButton;
    JComboBox<Integer> sizeButton;
    JButton cbButton;
    JButton boldButton;
    JButton italicsButton;
    JButton underlineButton;

    Toolbar() {
        this.setPreferredSize(new Dimension(400, 25));
        this.setLayout(new BorderLayout());

        JPanel side = new JPanel();
        side.setLayout(new FlowLayout(FlowLayout.CENTER, 5, -1));
        JPanel text = new JPanel();
        text.setLayout(new FlowLayout(FlowLayout.CENTER, 5, -1));

        newButton = new JButton("New");
        newButton.addActionListener(this);
        delButton = new JButton("Delete");
        delButton.addActionListener(this);
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        side.add(newButton);
        side.add(delButton);
        side.add(saveButton);

        fontButton = new JComboBox<String>(new String[] {"Calibri", "Times", "Arial", "Helvetica", "Roboto"});
        fontButton.addActionListener(this);

        sizeButton = new JComboBox<Integer>(new Integer[] { 6, 12, 14, 16, 20, 24, 32, 44, 56 });
        sizeButton.setSelectedIndex(2);
        sizeButton.addActionListener(this);

        JPanel font = new JPanel();
        font.setLayout(new FlowLayout(FlowLayout.CENTER, 0, -1));
        font.add(sizeButton);
        font.add(fontButton);

        boldButton = new JButton("B");
        boldButton.setPreferredSize(new Dimension(25,25));
        boldButton.addActionListener(this);
        italicsButton = new JButton("I");
        italicsButton.setPreferredSize(new Dimension(25,25));
        italicsButton.addActionListener(this);
        underlineButton = new JButton("U");
        underlineButton.setPreferredSize(new Dimension(25,25));
        underlineButton.addActionListener(this);

        JPanel style = new JPanel();
        style.setLayout(new FlowLayout(FlowLayout.CENTER, 0, -1));
        style.add(boldButton);
        style.add(italicsButton);
        style.add(underlineButton);

        text.add(font);
        text.add(style);

        this.add(side, BorderLayout.WEST);
        this.add(text, BorderLayout.EAST);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == newButton) {
            Sidebar.newNote();
        } else if (e.getSource() == delButton) {
            Sidebar.delNote();
        } else if (e.getSource() == saveButton) {
            Sidebar.saveNote();
        } else if (e.getSource() == fontButton || e.getSource() == sizeButton) {
            Notefield.setFont((String) fontButton.getSelectedItem(), (Integer) sizeButton.getSelectedItem());
        } else if (e.getSource() == boldButton) {
            Notefield.setStyle(1);
        } else if (e.getSource() == italicsButton) {
            Notefield.setStyle(2);
        } else if (e.getSource() == underlineButton) {
            Notefield.setStyle(3);
        }
    }
}
