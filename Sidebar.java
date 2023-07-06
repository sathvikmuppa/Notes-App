import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Sidebar extends JPanel {

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

        generateButtons();

        lastClicked = buttons.get(0);
        buttons.get(0).setSelected(true);

        scroll = new JScrollPane(panel);
        this.add(scroll, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private static void generateButtons() {
        buttons.clear();
        for (int x = 0; x < files.size(); x++) {

            JButton button = new JButton(files.get(x).getName().substring(0, files.get(x).getName().lastIndexOf('.')));
            button.setPreferredSize(new Dimension(175, 75));
            button.addActionListener(e -> {
                if (e.getSource() instanceof JButton) {
                    lastClicked.setSelected(false);
                    Notefield.setText(getText(new File("./Notes/" + ((JButton) e.getSource()).getText() + ".txt")));
                    ((JButton) e.getSource()).setSelected(true);
                    lastClicked = ((JButton) e.getSource());

                }
            });

            buttons.add(button);
            panel.add(button);
        }
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

        for (File f : arr) {
            if (f.getName().contains(".txt"))
                output.add(f);
        }

        return output;
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

    public static void newNote() {
        File blank = new File("./Notes/Unnamed.txt");
        lastClicked.setSelected(false);

        try {
            if (blank.createNewFile()) {
                Notefield.setText(getText(blank));
                JButton blankButton = new JButton("Unnamed");
                buttons.add(0, blankButton);
                files.add(0, new File("./Notes/Unnamed.txt"));

                panel.add(blankButton, 0);
                panel.setLayout(new GridLayout(files.size(), 1));

            } else {
                lastClicked.setSelected(false);
                int index = 0;
                for (int x = 0; x < files.size(); x++) {
                    if (files.get(x).getName().equals("Unnamed.txt"))
                        index = x;
                }

                buttons.add(0, buttons.get(index));
                buttons.remove(index + 1);
                files.add(0, files.get(index));
                files.remove(index + 1);

                files.get(0).setLastModified(System.currentTimeMillis());

                Notefield.setText(getText(blank));
            }

            panel.revalidate();
            lastClicked = buttons.get(0);
            buttons.get(0).setSelected(true);

        } catch (Exception e) {}
    }

    public static void delNote() {
        File del = new File("./Notes/" + lastClicked.getText() + ".txt");
        if (del.exists()) {   
            System.out.println(lastClicked.getText());
            del.delete();
            int index = findFileIndex(del);
            panel.remove(buttons.get(index));
            buttons.remove(index);
            files.remove(index);

            panel.setLayout(new GridLayout(files.size(), 1));
            panel.revalidate();

            lastClicked = buttons.get(0);
            lastClicked.setSelected(true);
            Notefield.setText(getText(new File("./Notes/" + lastClicked.getText() + ".txt")));
        }

    }

    public static int findFileIndex(File file) {
        int index = 0;
        for (int x = 0; x < files.size(); x++) {
            if (files.get(x).getName().equals(file.getName()))
                index = x;
        }
        return index;
    }

    public static void saveNote() {
        File save = new File("./Notes/" + lastClicked.getText() + ".txt");

        Scanner sc = new Scanner(Notefield.getText());
        String s = sc.nextLine().trim();
        sc.close();
        
        String path = "";
        files.remove(save);

        if (s.length() >= 20) {
            path = "./Notes/" + s.substring(0, 20) + "-.txt";
            save.renameTo(new File(path));
            save.delete();
            save = new File(path);
        } else if (s.length() > 0) {
            path = "./Notes/" + s + ".txt";
            save.renameTo(new File(path));
            save.delete();
            save = new File(path);
        } else {
            path = "./Notes/Unnamed.txt";
            save.renameTo(new File(path));
            save.delete();
            save = new File(path);
        }

        files.add(0, save);

        try {
            FileWriter write = new FileWriter("./Notes/" + save.getName());
            write.write(Notefield.getText());
            write.close();
        } catch (Exception E) {
            System.out.println("Unable to write file");
        }

        panel.removeAll();
        generateButtons();
        buttons.get(0).setSelected(true);
        lastClicked = buttons.get(0);
        panel.revalidate();

    }
}
