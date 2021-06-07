import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class test {
    public static void main(String args[]) {
        JFrame frame = new JFrame("TextPane Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StyleContext context = new StyleContext();
        StyledDocument document = new DefaultStyledDocument(context);

        Style labelStyle = context.getStyle(StyleContext.DEFAULT_STYLE);

        Icon icon = new ImageIcon("Resources/Dark_moon_30px.png");
        JLabel label = new JLabel(icon);
        StyleConstants.setComponent(labelStyle, label);

        try {
            document.insertString(document.getLength(), "Ignored", labelStyle);
        } catch (BadLocationException badLocationException) {
            System.err.println("Oops");
        }

        JTextPane textPane = new JTextPane(document);
        textPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textPane);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setSize(300, 150);
        frame.setVisible(true);
    }
}