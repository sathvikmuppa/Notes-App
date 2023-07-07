import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.font.TextAttribute;
import java.io.File;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Notefield extends JPanel {

    private static JTextArea text;
    private static String[] fontattr = { "SansSerif", "0", "14" };
    private static Boolean bolded = false;
    private static Boolean italicized = false;
    private static Boolean underlined = false;

    Notefield() {
        this.setLayout(new BorderLayout());

        text = new JTextArea();
        text.setMargin(new Insets(5, 5, 5, 5));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        text.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        setText(Sidebar.getText(Sidebar.getRecent()));

        JScrollPane scroll = new JScrollPane(text);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scroll, BorderLayout.CENTER);
    }

    public static void setText(String s) {
        text.setText(s);
        text.setVisible(true);
        text.setCaretPosition(0);
    }

    public static String getText() {
        return text.getText();
    }

    public static void setFont(String font, int size) {
        text.setFont(new Font(font, Font.PLAIN, size));
        fontattr[0] = font;
        fontattr[1] = size + "";
    }

    public static void setStyle(int which) {
        Font font = text.getFont();
        Map attributes = font.getAttributes();

        if (which < 2) {
            if (italicized) {
                if (bolded) {
                    text.setFont(new Font(fontattr[0], Font.PLAIN | Font.ITALIC, Integer.valueOf(fontattr[2])));
                    bolded = false;
                    fontattr[1] = "2";
                } else {
                    text.setFont(new Font(fontattr[0], Font.BOLD | Font.ITALIC, Integer.valueOf(fontattr[2])));
                    bolded = true;
                    fontattr[1] = "3";
                }
            } else {
                if (bolded) {
                    text.setFont(new Font(fontattr[0], Font.PLAIN, Integer.valueOf(fontattr[2])));
                    bolded = false;
                    fontattr[1] = "0";
                } else {
                    text.setFont(new Font(fontattr[0], Font.BOLD, Integer.valueOf(fontattr[2])));
                    bolded = true;
                    fontattr[1] = "1";
                }
            }
        } else if (which == 2) {
            if (bolded) {
                if (italicized) {
                    text.setFont(new Font(fontattr[0], Font.PLAIN | Font.BOLD, Integer.valueOf(fontattr[2])));
                    italicized = false;
                    fontattr[1] = "1";
                } else {
                    text.setFont(new Font(fontattr[0], Font.ITALIC | Font.BOLD, Integer.valueOf(fontattr[2])));
                    italicized = true;
                    fontattr[1] = "3";
                }
            } else {
                if (italicized) {
                    text.setFont(new Font(fontattr[0], Font.PLAIN, Integer.valueOf(fontattr[2])));
                    italicized = false;
                    fontattr[1] = "0";
                } else {
                    text.setFont(new Font(fontattr[0], Font.ITALIC, Integer.valueOf(fontattr[2])));
                    italicized = true;
                    fontattr[1] = "2";
                }
            }
        } else if (which == 3) {
            if (underlined) {
                attributes.remove(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                text.setFont(new Font(fontattr[0], Integer.valueOf(fontattr[1]), Integer.valueOf(fontattr[2])));
                underlined = false;
                System.out.println("false");
            } else {
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                text.setFont(font.deriveFont(attributes));
                underlined = true;
                System.out.println("true");
            }
        }
    }
}
