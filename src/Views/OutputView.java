package Views;

import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static javax.swing.JOptionPane.*;
import static Presenters.InputPresenter.*;
import Presenters.OutputPresenter;
import Presenters.UserPresenter;
import Views.UI.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OutputView {
    private static final AppFrame appFrame = new AppFrame();
    public static final Color INDEPENDENCE = new Color(74, 78, 105);
    public static final Color HELIOTROPE_GRAY = new Color(154, 140, 152);
    public static final Color ISABELLINE = new Color(242, 233, 228);
    public static final IPanel mainPanel = new MainPanel();
    public static final IPanel loginPanel = new LoginPanel();
    public static final IPanel signUpPanel = new SignupPanel();

    public static void SetUpGUI() {
        appFrame.SetupOnTimeConfiguration();
        appFrame.SetCurrentPanel(new LoginPanel());
    }

    //<editor-fold desc="On-Events Actions">
    public static void OnClick_Logout(){
        UserPresenter.LogOut();
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
    public static void OnClick_SendMessage(JLabel userName, JTextArea inputArea, JTextArea chatArea){
        if (inputArea.getText().trim().isEmpty()) return;

        String message = inputArea.getText();
        String userNameIndentation = String.format("%s: ", userName.getText().trim());
        int indentationLength = userNameIndentation.length();
        String indentation = "\n".concat(" ".repeat(indentationLength));
        message = message.replace("\n", indentation);
        String formattedMessage = String.format("%s%s\n", userNameIndentation, message);
        chatArea.append(formattedMessage);
        inputArea.setText("");
    }
    //</editor-fold>
    public static String GetCurrentUser(){ return OutputPresenter.GetCurrentUser(); }

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
    public static void SetupInputFields(JTextField... inputFields) {
        for (JTextField inputField : inputFields) {
            inputField.setBackground(HELIOTROPE_GRAY);
            inputField.setForeground(ISABELLINE);
            inputField.setBorder(null);
        }
    }
    public static void SetupLogoPanelLayout(JPanel logoPanel, JLabel appLogo, JLabel appName) {
        var logoPanelLayout = new GroupLayout(logoPanel);

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
        var mainPanelLayout = new GroupLayout(hostPanel);

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
}