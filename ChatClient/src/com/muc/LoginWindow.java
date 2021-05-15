package com.muc;

import com.muc.Views.UI.IPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static com.muc.Views.OutputView.*;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;

public class LoginWindow extends JPanel implements IPanel, ActionListener {
    //<editor-fold desc="Variables Declarations">">
    private final JPanel logoPanel = new JPanel();
    private final JPanel inputPanel = new JPanel();
    private final JSeparator loginSeparator = new JSeparator();
    private final JSeparator passwordSeparator = new JSeparator();
    private final JLabel appLogo = new JLabel(new ImageIcon("resources/Images/chat_bubble_127px.png"));
    private final JLabel passwordLabel = new JLabel(new ImageIcon("resources/Images/password_45px.png"));
    private final JLabel loginLabel = new JLabel(new ImageIcon("resources/Images/login_45px.png"));
    private final JLabel appName = new JLabel("Chat Lab");
    private final JButton signInButton = new JButton(new ImageIcon("resources/Images/login_80px.png"));
    private final JButton signUpButton = new JButton(new ImageIcon("resources/Images/add_80px.png"));
    private final JPasswordField passwordField = new JPasswordField();
    private final JTextField loginField = new JTextField();
    private ChatClient client;
    public static String strLogin;
    //</editor-fold>

    //<editor-fold desc="FrontEnd Stuff">
    private void SetupLogoPanel() {
        logoPanel.setBackground(INDEPENDENCE);
        SetupLogoTextPanel();
        SetupLogoPanelLayout(logoPanel, appLogo, appName);
    }
    private void SetupLogoTextPanel() {
        appName.setForeground(HELIOTROPE_GRAY);
        appName.setFont(new Font("Source Code Pro", Font.PLAIN, 48));
    }
    private void SetupInputPanel() {
        inputPanel.setBackground(HELIOTROPE_GRAY);
        SetupInputFields(loginField, passwordField);
        SetupSeparators(loginSeparator, passwordSeparator);
        SetupSubmitButton(signInButton, this, true, "Click to login");
        SetupSubmitButton(signUpButton, this, true, "Click to creat an account");
        SetupInputPanelLayout();
    }
    private void SetupSeparators(JSeparator... separators) {
        for (JSeparator separator : separators) {
            separator.setBackground(ISABELLINE);
        }
    }
    private void SetupInputFields(JTextField... inputFields) {
        for (JTextField inputField : inputFields) {
            inputField.setBackground(HELIOTROPE_GRAY);
            inputField.setForeground(ISABELLINE);
            inputField.setBorder(null);
        }
    }
    private void SetupInputPanelLayout() {
        GroupLayout inputPanelLayout = new GroupLayout(inputPanel);

        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
                inputPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(inputPanelLayout.createSequentialGroup()
                                .addGroup(inputPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addGap(83, 83, 83)
                                                .addGroup(inputPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(passwordLabel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(loginLabel))
                                                .addGap(26, 26, 26)
                                                .addGroup(inputPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(loginSeparator)
                                                        .addComponent(loginField)
                                                        .addComponent(passwordField)
                                                        .addComponent(passwordSeparator, GroupLayout.Alignment.TRAILING, PREFERRED_SIZE, 226, PREFERRED_SIZE)))
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addGap(142, 142, 142)
                                                .addComponent(signInButton, PREFERRED_SIZE, 71, PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(signUpButton, PREFERRED_SIZE, 71, PREFERRED_SIZE)))
                                .addContainerGap(73, Short.MAX_VALUE))
        );
        inputPanelLayout.setVerticalGroup(
                inputPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(inputPanelLayout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addGroup(inputPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addComponent(loginField, PREFERRED_SIZE, 40, PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(loginSeparator, PREFERRED_SIZE, 10, PREFERRED_SIZE))
                                        .addComponent(loginLabel))
                                .addGap(24, 24, 24)
                                .addGroup(inputPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addComponent(passwordField, PREFERRED_SIZE, 39, PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(passwordSeparator, PREFERRED_SIZE, 13, PREFERRED_SIZE))
                                        .addComponent(passwordLabel))
                                .addGap(29, 29, 29)
                                .addGroup(inputPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(signUpButton, PREFERRED_SIZE, 71, PREFERRED_SIZE)
                                        .addComponent(signInButton, PREFERRED_SIZE, 71, PREFERRED_SIZE))
                                .addContainerGap(160, Short.MAX_VALUE))
        );
    }
    private void SetupMainPanel() {
        SetupMainPanelLayout(logoPanel, inputPanel, this);
    }

    @Override
    public JPanel GetPanel() {
        return this;
    }

    @Override
    public void Activate() {
        setVisible(true);
    }

    @Override
    public void Deactivate() {
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(signInButton)) OnClick_SignIn(loginField, passwordField);
        //else if (event.getSource().equals(signUpButton)) OnClick_SwapPanels(signUpPanel);
    }
    //</editor-fold>


    public LoginWindow() {
        this.setPreferredSize(new Dimension(900, 500));
        SetupLogoPanel();
        SetupInputPanel();
        SetupMainPanel();

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(loginField);
        p.add(passwordField);
        p.add(signInButton);
        signInButton.addActionListener(e -> doLogin());
        setVisible(true);
    }

    private void doLogin() {
        strLogin = loginField.getText();
        String password = passwordField.getText();

        try {
            this.client = new ChatClient("localhost", 8818);
            client.connect();

            if (client.login(strLogin, password)) {

                UserListPane userOnlineList = new UserListPane(client);
                JFrame frame = new JFrame(strLogin + " - Online User List");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 600);
                frame.getContentPane().add(userOnlineList, BorderLayout.CENTER);
                frame.setVisible(true);


                UserPaneOffLine userListOffLine = new UserPaneOffLine(client);
                JFrame jframe = new JFrame(strLogin + " - Offline User List");
                jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jframe.setSize(400, 600);
                jframe.getContentPane().add(userListOffLine, BorderLayout.CENTER);
                jframe.setVisible(true);


                MessageGrpPane messageGrpPane = new MessageGrpPane(client, strLogin);
                JFrame f = new JFrame(strLogin + " - Message en Groupe");
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setSize(500, 500);
                f.getContentPane().add(messageGrpPane, BorderLayout.CENTER);
                f.setVisible(true);
                setVisible(false);
            } else {
                // show error message
                JOptionPane.showMessageDialog(this, "Invalid login/password.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLogg() {
        return strLogin;
    }

    public static void main(String[] args) {
        LoginWindow loginWin = new LoginWindow();
        loginWin.setVisible(true);
    }
}
