package com.muc.Views;

import static com.muc.Controllers.InputController.Try_SignIn;
import static com.muc.Controllers.InputController.Try_SignUp;
import static javax.swing.JOptionPane.*;

import com.muc.*;
import com.muc.Controllers.OutputController;
import com.muc.Controllers.UserController;
import com.muc.Views.UI.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class OutputView {
    private static final AppFrame appFrame = new AppFrame();
    public static final Color INDEPENDENCE = new Color(74, 78, 105);
    public static final Color HELIOTROPE_GRAY = new Color(154, 140, 152);
    public static final Color ISABELLINE = new Color(242, 233, 228);
    public static final IPanel loginPanel = new LoginPanel();
    public static MainPanel mainPanel;
    public static final IPanel signUpPanel = new SignupPanel();
    private static JTextArea chatField = SetupChatArea();// = ((MainPanel) mainPanel).chatArea;
    private static final LoginWindow loginWindow = new LoginWindow();

    public static void SetUpGUI() {
        appFrame.SetupOnTimeConfiguration();
        appFrame.SetCurrentPanel(loginWindow);
    }

    //<editor-fold desc="On-Events Actions">
    public static void OnClick_Logout() {
        UserController.LogOut();
        OnClick_SwapPanels(loginPanel);
    }
    public static void OnClick_SignUp(JTextField login, JPasswordField password, JPasswordField passwordRepeat) {
        String strLogin = login.getText().trim();
        String strPassword = String.valueOf(password.getPassword()).trim();
        String strPasswordRepeat = String.valueOf(passwordRepeat.getPassword()).trim();
        Try_SignUp(strLogin, strPassword, strPasswordRepeat);
    }
    public static void OnClick_SignIn(JTextField login, JPasswordField password) {
        String strLogin = login.getText();
        String strPassword = String.valueOf(password.getPassword());
        if (Try_SignIn(strLogin, strPassword)) {
            Server_Login(strLogin, strPassword);
            //OnClick_SwapPanels(new MainPanel(client));
        }
    }
    public static void OnClick_SwapPanels(IPanel gotoPanel) {
        appFrame.GetCurrentPanel().setVisible(false);
        appFrame.SetCurrentPanel(gotoPanel);
    }
    public static void OnClick_SendMessage(JLabel userName, JTextArea inputField, JTextArea chatArea) {
        if (inputField.getText().trim().isEmpty()) return;
    }
    //</editor-fold>

    public static String GetCurrentUser() {
        return OutputController.GetCurrentUser();
    }
    private static String FormatMessage(JLabel userName, JTextArea inputArea) {
        String message = inputArea.getText().trim();
        String userNameIndentation = String.format("%s: ", userName.getText().trim());
        int indentationLength = userNameIndentation.length();
        String indentation = "\n".concat(" ");
        message = message.replace("\n", indentation);
        return String.format("%s%s\n", userNameIndentation, message);
    }

    //<editor-fold desc="Setup Comment Components">
    public static void SetupSubmitButton(JButton submitButton, ActionListener actionListener, boolean isEnabled, String toolTip) {
        //todo: add some feed back on clicking the buttons
        submitButton.setBackground(HELIOTROPE_GRAY);
        submitButton.setForeground(ISABELLINE);
        submitButton.setBorderPainted(false);
        submitButton.setToolTipText(toolTip);
        submitButton.setEnabled(isEnabled);
        submitButton.setContentAreaFilled(false);
        submitButton.setFocusPainted(false);
        submitButton.setFocusable(false);
        submitButton.setOpaque(false);
        submitButton.addActionListener(actionListener);
    }
    public static void SetupSeparators(JSeparator... separators) {
        for (JSeparator separator : separators) {
            separator.setBackground(ISABELLINE);
        }
    }
    public static void SetupInputFields(KeyListener parentPanel, JTextField... inputFields) {
        for (JTextField inputField : inputFields) {
            inputField.setBackground(HELIOTROPE_GRAY);
            inputField.setForeground(ISABELLINE);
            inputField.setBorder(null);
            inputField.addKeyListener(parentPanel);
        }
    }
    public static void SetupLogoPanelLayout(JPanel logoPanel, JLabel appLogo, JLabel appName) {
        GroupLayout logoPanelLayout = new GroupLayout(logoPanel);

        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
                logoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(logoPanelLayout.createSequentialGroup()
                                .addGroup(logoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(logoPanelLayout.createSequentialGroup()
                                                .addGap(165, 165, 165)
                                                .addComponent(appLogo))
                                        .addGroup(logoPanelLayout.createSequentialGroup()
                                                .addGap(107, 107, 107)
                                                .addComponent(appName)))
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        logoPanelLayout.setVerticalGroup(
                logoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(logoPanelLayout.createSequentialGroup()
                                .addContainerGap(131, Short.MAX_VALUE)
                                .addComponent(appLogo)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(appName, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                                .addGap(160, 160, 160))
        );
    }
    public static void SetupMainPanelLayout(JPanel logoPanel, JPanel inputPanel, JPanel hostPanel) {
        GroupLayout mainPanelLayout = new GroupLayout(hostPanel);

        hostPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }
    public static JTextArea SetupChatArea() {
        JTextArea textArea = new JTextArea();
        textArea.setBackground(HELIOTROPE_GRAY);
        textArea.setColumns(20);
        textArea.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
        textArea.setForeground(ISABELLINE);
        textArea.setRows(5);
        textArea.setText("Welcome !\n");
        textArea.setFocusable(false);
        textArea.setLineWrap(true);
        return textArea;
    }

    //</editor-fold>

    private static void Server_Login(String strLogin, String strPassword) {
        try {
            ChatClient client = new ChatClient("localhost", 8818);
            client.connect();
            if (client.login(strLogin, strPassword)) {
                mainPanel = new MainPanel(client);
                OnClick_SwapPanels(mainPanel);

                UserListPane userListPane = new UserListPane(client);
                JFrame frame = new JFrame(strLogin + " - Online User List");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 600);
                frame.getContentPane().add(userListPane, BorderLayout.CENTER);
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
            } else {
                DisplayError("Server Error: Invalid login/password.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static JTextArea GetChatField(){
        chatField = SetupChatArea();
        return chatField; }
    public static void SetChatField(JTextArea chatArea){ chatField = chatArea; }
    public static void DisplayInformation(String greeting) {
        showMessageDialog(null, greeting, "Greeting", INFORMATION_MESSAGE);
    }
    public static void DisplayError(String error) {
        showMessageDialog(null, error, "Error", ERROR_MESSAGE);
    }
    public static int DisplayConfirmation() {
        return JOptionPane.showConfirmDialog(null, "You sure you wanna delete this ?",
                "Confirmation", YES_NO_OPTION);
    }
}