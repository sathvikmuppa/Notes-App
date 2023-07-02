import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Sidebar extends JPanel {
    Sidebar() {
        this.setPreferredSize(new Dimension(200, 400));
        this.setBackground(Color.gray);
        this.setLayout(new BorderLayout());

        File folder = new File("./Notes");
        File[] files = listFiles(folder);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(files.length, 1));

        for (int x = 0; x < files.length; x++) {

            JButton button = new JButton(files[x].getName().substring(0, files[x].getName().lastIndexOf('.')));
            button.setPreferredSize(new Dimension(175, 75));

            panel.add(button);
        }

        JScrollPane scroll = new JScrollPane(panel);
        this.add(scroll, BorderLayout.CENTER);

    }

    private File[] listFiles(File file) {
        File[] output = file.listFiles();
        
        for (int i = 0; i < output.length; i++) {
            for (int x = 0; x < output.length-1; x++) {
                if (output[x].lastModified() < output[x+1].lastModified()) {
                    File temp = output[x];
                    output[x] = output[x+1];
                    output[x+1] = temp;
        }}}

        return output;
    } 
}
