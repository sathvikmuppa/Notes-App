import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener {

    JButton newButton;
    JButton delButton;
    JComboBox<String> fontButton;
    JButton cbButton;
    JButton tableButton;
    JButton imageButton;
    JButton saveButton;

    Toolbar() {
        this.setPreferredSize(new Dimension(400, 27));
        this.setLayout(new BorderLayout());

        JPanel side = new JPanel();
        side.setLayout(new GridLayout(1, 2, 5, 0));
        JPanel text = new JPanel();
        text.setLayout(new GridLayout(1, 4, 5, 0));

        newButton = new JButton("New");
        newButton.addActionListener(this);
        delButton = new JButton("Delete");
        delButton.addActionListener(this);
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        side.add(newButton);
        side.add(delButton);
        side.add(saveButton);

        fontButton = new JComboBox<String>(new String[] { "Font", "Calilbri", "Times", "Ariel" });
        text.add(fontButton);

        cbButton = new JButton("Checklist");
        tableButton = new JButton("Table");
        imageButton = new JButton("Image");
        text.add(cbButton);
        text.add(tableButton);
        text.add(imageButton);

        this.add(side, BorderLayout.WEST);
        this.add(text, BorderLayout.EAST);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newButton) {
            Sidebar.newNote();
        } else if (e.getSource() == delButton) {
            Sidebar.delNote();
        } else if (e.getSource() == saveButton) {

        } else if (e.getSource() == fontButton) {

        } else if (e.getSource() == cbButton) {

        } else if (e.getSource() == tableButton) {

        } else if (e.getSource() == imageButton) {

        }
    }
}
