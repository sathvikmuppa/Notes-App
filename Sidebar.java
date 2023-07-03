import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Sidebar extends JPanel implements ActionListener {

    private static ArrayList<JButton> buttons;
    private static ArrayList<File> files;
    private static JButton lastClicked = new JButton();
    private static JPanel panel;
    private static JScrollPane scroll;

    Sidebar() {
        this.setPreferredSize(new Dimension(200, 400));
        this.setMinimumSize(new Dimension(100, 400));
        this.setBackground(Color.gray);
        this.setLayout(new BorderLayout());

        File folder = new File("./Notes");
        files = listFiles(folder);

        panel = new JPanel();
        panel.setLayout(new GridLayout(files.size(), 1));

        buttons = new ArrayList<JButton>();

        for (int x = 0; x < files.size(); x++) {

            JButton button = new JButton(files.get(x).getName().substring(0, files.get(x).getName().lastIndexOf('.')));
            button.setPreferredSize(new Dimension(175, 75));
            button.addActionListener(this);

            buttons.add(button);
            panel.add(button);
        }

        lastClicked = buttons.get(0);

        scroll = new JScrollPane(panel);
        this.add(scroll, BorderLayout.CENTER);
        System.out.println(files);
        this.setVisible(true);
    }

    private ArrayList<File> listFiles(File file) {
        File[] arr = file.listFiles();
        ArrayList<File> output = new ArrayList<File>();

        for (int i = 0; i < arr.length; i++) {
            for (int x = 0; x < arr.length - 1; x++) {
                if (arr[x].lastModified() < arr[x + 1].lastModified()) {
                    File temp = arr[x];
                    arr[x] = arr[x + 1];
                    arr[x + 1] = temp;
                }
            }
        }

        for (File f: arr) {
            if (f.getName().contains(".txt"))
                output.add(f);
        }

        return output;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            lastClicked.setSelected(false);
            Notefield.setText(getText(new File("./Notes/" + ((JButton) e.getSource()).getText() + ".txt")));
            ((JButton) e.getSource()).setSelected(true);
            lastClicked = ((JButton) e.getSource());

        }
    }

    public static String getText(File note) {
        String s = "";
        try {
            Scanner sc = new Scanner(note);
            while (sc.hasNextLine()) {
                s += sc.nextLine() + "\n";
            }
        } catch (Exception exc) {
            System.out.println("File not found");
        }

        return s;
    }

    public static File getRecent() {
        return files.get(0);
    }

    public static void setButton(int i) {
        buttons.get(i).setSelected(true);
    }

    public static void newNote() {
        File blank = new File("./Notes/Unnamed.txt");

        try {
            if (blank.createNewFile()) {
                Notefield.setText(getText(blank));
                JButton blankButton = new JButton("Unnamed");
                buttons.add(0,blankButton);
                files.add(0, new File("./Notes/Unnamed.txt"));

                panel.add(blankButton,0);
                panel.setLayout(new GridLayout(files.size(), 1));
                
                panel.revalidate();

            } else {
                System.out.println(files);
                lastClicked.setSelected(false);
                int index = 0;
                for (int x = 0; x < files.size(); x++) {
                    if (files.get(x).getName().equals("Unnamed.txt")) 
                        index = x;
                }

                JButton tempb = buttons.get(0);
                buttons.set(0,buttons.get(index));
                buttons.set(index, tempb);
                File tempf = files.get(0);
                files.set(0, files.get(index));
                files.set(index, tempf);

                System.out.println(files);
                System.out.println(files.get(0).setLastModified(System.currentTimeMillis()));

                Notefield.setText(getText(blank));
            }

            lastClicked=buttons.get(0);
            setButton(0);

        } catch (Exception e) {}

    }

    public static void delNote() {
        File blank = new File("./Notes/Unnamed.txt");
        blank.delete();
        //panel.remove(findFileIndex())
        panel.revalidate();
    }

    public static int findFileIndex(String name) {
        int index = 0;
        for (int x = 0; x < files.size(); x++) {
                    if (files.get(x).getName().equals(name)) 
                        index = x;
        }
        return index;

    }


}
