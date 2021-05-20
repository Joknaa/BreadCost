package com.muc.NewUI;

import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.JOptionPane.*;
import static com.muc.Controllers.InputController.*;

import com.muc.ChatClient;
import com.muc.Controllers.OutputController;
import com.muc.Controllers.UserController;
import com.muc.MessageGrpPane;
import com.muc.UserListPane;
import com.muc.UserPaneOffLine;

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
    public static final IPanel signUpPanel = new SignupPanel();

    public static void SetUpGUI() {
        appFrame.SetupOnTimeConfiguration();
        appFrame.SetCurrentPanel(new LoginPanel());
    }

    //<editor-fold desc="On-Events Actions">
    public static void OnClick_Logout(){
        UserController.LogOut();
        OnClick_SwapPanels(loginPanel);
    }
    public static void OnClick_SignUp(JTextField login, JPasswordField password, JPasswordField passwordRepeat) {
        String strLogin = login.getText().trim();
        String strPassword = String.valueOf(password.getPassword()).trim();
        String strPasswordRepeat = String.valueOf(passwordRepeat.getPassword()).trim();
        Try_SignUp(strLogin, strPassword, strPasswordRepeat);
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
    public static String GetCurrentUser(){ return OutputController.GetCurrentUser(); }
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
    public static int DisplayConfirmation() {
        return JOptionPane.showConfirmDialog(null, "You sure you wanna delete this ?",
                "Confirmation", YES_NO_OPTION);
    }
    public static class OnMouseClick_CloseApp extends MouseAdapter {
        public void mouseClicked(MouseEvent e) { System.exit(0); }
    }


    public static void Login(String strLogin, String strPassword) {
        System.out.println("Login in progress ..........");
        ChatClient client = new ChatClient("localhost", 8818);
        client.connect();
        try {
            if (client.login(strLogin, strPassword)) {
                IPanel MainPanel = new MainPanel(client, strLogin);
                OnClick_SwapPanels(MainPanel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}