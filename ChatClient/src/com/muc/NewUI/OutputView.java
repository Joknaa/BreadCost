package com.muc.NewUI;

import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.JOptionPane.*;
import static com.muc.Controllers.InputController.*;

import com.muc.*;
import com.muc.Controllers.OutputController;
import com.muc.Controllers.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class OutputView {
    private static final AppFrame appFrame = new AppFrame();
    public static final Color INDEPENDENCE = new Color(74,78,105);
    public static final Color HELIOTROPE_GRAY = new Color(154,140,152);
    public static final Color ISABELLINE = new Color(244,252,231);
    public static final IPanel loginPanel = new LoginPanel();
    public static final IPanel signUpPanel = new SignupPanelold();
    public static String currentUser;
    public static ChatClient client;

    public static void SetUpGUI() {
        appFrame.SetupOnTimeConfiguration();
        appFrame.SetCurrentPanel(new LoginPanel());
    }

    //<editor-fold desc="On-Events Actions">
    public static void OnClick_Logout(){
        UserController.LogOut();
        if (client == null) System.exit(0);
        client.logoff();
        OnClick_SwapPanels(loginPanel);
    }
    public static void OnClick_SignUp(JTextField login, JPasswordField password, JPasswordField passwordRepeat) {
        String strLogin = login.getText().trim();
        String strPassword = String.valueOf(password.getPassword()).trim();
        String strPasswordRepeat = String.valueOf(passwordRepeat.getPassword()).trim();
        //Try_SignUp(strLogin, strPassword, strPasswordRepeat);
        Signup(strLogin, strPassword, strPasswordRepeat);
    }
    public static void OnClick_SignIn(JTextField login, JPasswordField password){
        String strLogin = login.getText();
        String strPassword = String.valueOf(password.getPassword());
        Try_SignIn(strLogin, strPassword);
    }
    public static void OnClick_SwapPanels(IPanel gotoPanel){
        appFrame.GetCurrentPanel().setVisible(false);
        appFrame.SetCurrentPanel(gotoPanel);
    }

    //</editor-fold>
    public static String GetCurrentUser(){ return currentUser; }
    //<editor-fold desc="Setup Comment Components">
    public static void SetupCloseButton(JButton closeButton){
        closeButton.setBackground(HELIOTROPE_GRAY);
        closeButton.setFont(new Font("Source Code Pro", Font.PLAIN, 24));
        closeButton.setForeground(INDEPENDENCE);
        closeButton.setText("X");
        closeButton.setToolTipText("Close");
        closeButton.setBorder(null);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.setFocusable(false);
        closeButton.setMaximumSize(new Dimension(100, 38));
        closeButton.setMinimumSize(new Dimension(100, 38));
        closeButton.setPreferredSize(new Dimension(100, 38));
        closeButton.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        closeButton.addMouseListener(new OnMouseClick_CloseApp());
    }
    public static void SetupSubmitButton(JButton submitButton, ActionListener actionListener, boolean isEnabled, String toolTip) {
        //todo: add some feed back on clicking the buttons
        submitButton.setBorderPainted(false);
        submitButton.setPreferredSize(new Dimension(100, 38));
        submitButton.setMaximumSize(new Dimension(100, 38));
        submitButton.setMinimumSize(new Dimension(100, 38));
        submitButton.setBackground(HELIOTROPE_GRAY);
        submitButton.setForeground(ISABELLINE);
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
    public static void SetupInputFields(JTextField... inputFields) {
        for (JTextField inputField : inputFields) {
            inputField.setBackground(HELIOTROPE_GRAY);
            inputField.setForeground(ISABELLINE);
            inputField.setBorder(null);
        }
    }
    public static void SetupLogoPanelLayout(JPanel logoPanel, JLabel LogoIconPanel, JLabel LogoTextPanel) {
        GroupLayout logoPanelLayout = new GroupLayout(logoPanel);

        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
                logoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(logoPanelLayout.createSequentialGroup()
                                .addGap(165, 165, 165)
                                .addComponent(LogoIconPanel)
                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, logoPanelLayout.createSequentialGroup()
                                .addContainerGap(105, Short.MAX_VALUE)
                                .addComponent(LogoTextPanel)
                                .addGap(115, 115, 115))
        );
        logoPanelLayout.setVerticalGroup(
                logoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(logoPanelLayout.createSequentialGroup()
                                .addContainerGap(138, Short.MAX_VALUE)
                                .addComponent(LogoIconPanel)
                                .addGap(18, 18, 18)
                                .addComponent(LogoTextPanel)
                                .addGap(193, 193, 193))
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
    //</editor-fold>

    public static void DisplayInformation(String greeting) {
        showMessageDialog(null, greeting, "Greeting", INFORMATION_MESSAGE);
    }
    public static void DisplayError(String error) {
        showMessageDialog(null, error, "Error", ERROR_MESSAGE);
    }
    public static int DisplayConfirmation(String title, String message) {
        return JOptionPane.showConfirmDialog(null, message, title, YES_NO_OPTION);
    }
    public static class OnMouseClick_CloseApp extends MouseAdapter {
        public void mouseClicked(MouseEvent e) { System.exit(0); }
    }


    public static void Login(ChatClient client, String strLogin, String strPassword) {
        System.out.println("Login in progress ..........");
        if (client == null) {
            client = new ChatClient("localhost", 8818);
            client.connect();
        }
        try {
            if (client.login(strLogin, strPassword)) {
                currentUser = strLogin;
                //IPanel MainPanel = new MainPanel(client, strLogin);
                //OnClick_SwapPanels(MainPanel);
                UserListPane onlineListPanel = new UserListPane(client, strLogin);
                MessageGrpPane groupChatPanel = new MessageGrpPane(client, strLogin);
                UserPaneOffLine offlineListPanel = new UserPaneOffLine(client, strLogin);

                JFrame frame = new JFrame("BreadCost - " + strLogin);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(new Dimension(600, 400));
                frame.setVisible(true);
                frame.setResizable(false);

                frame.add(onlineListPanel, BorderLayout.WEST);
                frame.add(groupChatPanel, BorderLayout.CENTER);
                frame.add(offlineListPanel, BorderLayout.EAST);

                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                        String ObjButtons[] = {"Yes", "No"};
                        int PromptResult = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "Online Examination System", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                        if (PromptResult == JOptionPane.YES_OPTION) {
                            OnClick_Logout();
                            frame.dispose();
                            System.exit(0);
                        }
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Signup(String strLogin, String strPassword, String strPasswordRepeat){
        client = new ChatClient("localhost", 8818);
        client.connect();
        try {
            if ((strLogin.isEmpty() || strPassword.isEmpty() || strPasswordRepeat.isEmpty())) return;
            int signUpResult = client.signUp(strLogin, strPassword, strPasswordRepeat);
            switch (signUpResult) {
                case 1:
                    DisplayInformation("SignedUp Successfully ! Welcome " + strLogin);
                    //client.signUp(strLogin, strPassword, strPasswordRepeat);
                    break;
                case 2:
                    DisplayError("Username Already Exist !!");
                    break;
                case 3:
                    DisplayError("Passwords Doesn't Match !!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "ERROR!!", "Not SignedUp !!", 0, null);
                    break;
            }
        } catch (Exception s) {
            s.printStackTrace();
        }
    }
}