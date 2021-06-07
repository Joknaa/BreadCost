import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppendingTextPane2 extends JTextPane {
    static Icon ProfileImage = new ImageIcon("Dark_moon_30px.png");
    static String UserName = "Oknaa";
    static String Message = "Message goes here ..\n";


    public static void main(String[] args) {
        String title = (args.length == 0 ? "TextPane Example" : args[0]);
        JFrame frame = new JFrame(title);
        Container content = frame.getContentPane();

        StyleContext context = new StyleContext();
        StyledDocument document = new DefaultStyledDocument(context);

        Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setFontSize(style, 14);
        StyleConstants.setSpaceAbove(style, 4);
        StyleConstants.setSpaceBelow(style, 4);

        // Insert content
        try {
            document.insertString(document.getLength(), "message", style);
        } catch (BadLocationException badLocationException) {
            System.err.println("Oops");
        }

        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setBold(attributes, true);
        StyleConstants.setItalic(attributes, true);

        // Insert content
        try {
            document.insertString(document.getLength(), "Hello Java",
                    attributes);
        } catch (BadLocationException badLocationException) {
            System.err.println("Oops");
        }

        // Third style for icon/component
        Style labelStyle = context.getStyle(StyleContext.DEFAULT_STYLE);

        Icon icon = new ImageIcon("Dark_moon_30px.png");
        JLabel label = new JLabel(icon);
        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("pressed !!!");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("pressed !!!");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("pressed !!!");
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        StyleConstants.setComponent(labelStyle, label);

        // Insert content
        try {
            document.insertString(document.getLength(), "Ignored", labelStyle);
        } catch (BadLocationException badLocationException) {
            System.err.println("Oops");
        }

        JTextPane textPane = new JTextPane(document);
        textPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textPane);
        content.add(scrollPane, BorderLayout.CENTER);

        frame.setSize(300, 150);
        frame.setVisible(true);
    }
}

