import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppendingTextPane extends JTextPane {
    static Icon ProfileImage = new ImageIcon("Dark_moon_30px.png");
    static String UserName = "Oknaa";
    static String Message = "Message goes here ..\n";

    public AppendingTextPane() {
        super();
    }

    public AppendingTextPane(StyledDocument doc) {
        super(doc);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Text Pane with Scrolling Append");
        final AppendingTextPane atp = new AppendingTextPane();
        frame.getContentPane().add(new JScrollPane(atp));
        frame.setSize(300, 150);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        Timer t = new Timer(1000, new ActionListener() {
            final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");

            public void actionPerformed(ActionEvent evt) {
                String timeString = fmt.format(new Date());
                atp.appendText(timeString + "\n");
            }
        });
        t.start();
    }

    public void appendText(String text) {
        try {
            StyleContext context = new StyleContext();
            StyledDocument document = new DefaultStyledDocument(context);

            setCaretPosition(document.getLength());
            replaceSelection(text);

            Rectangle r = modelToView(document.getLength());
            if (r != null) {
                scrollRectToVisible(r);
            }
            // Third style for icon/component
            Style IconStyle = context.getStyle(StyleContext.DEFAULT_STYLE);
            Style UserNameStyle = context.getStyle(StyleContext.DEFAULT_STYLE);
            Style MessageStyle = context.getStyle(StyleContext.DEFAULT_STYLE);

            JLabel label = new JLabel(ProfileImage);
            label.setText(UserName);

            StyleConstants.setComponent(IconStyle, label);
            StyleConstants.setAlignment(UserNameStyle, StyleConstants.ALIGN_LEFT);
            StyleConstants.setAlignment(IconStyle, StyleConstants.ALIGN_LEFT);

            StyleConstants.setAlignment(MessageStyle, StyleConstants.ALIGN_LEFT);
            StyleConstants.setFontSize(MessageStyle, 14);
            StyleConstants.setSpaceAbove(MessageStyle, 4);
            StyleConstants.setSpaceBelow(MessageStyle, 4);

            //document.insertString(document.getLength(), "Ignored", IconStyle);
            replaceSelection(Message);
        } catch (BadLocationException e) {
            System.out.println("Failed to append text: " + e);
        }
    }
}

