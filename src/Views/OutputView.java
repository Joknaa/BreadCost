package Views;

import static Presenters.OutputPresenter.*;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
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
    public static final Color PICKLED_BLUEWOOD = new Color(52, 66, 91);
    public static final Color BLUE_BAYOUX = new Color(76, 96, 133);
    public static final Color BLUE_HAZE = new Color(190, 200, 218);
    public static final IPanel mainPanel = new MainPanel();
    public static final IPanel loginPanel = new LoginPanel();
    public static final IPanel signUpPanel = new SignupPanel();

    public static void SetUpGUI() {
        appFrame.SetupOnTimeConfiguration();
        appFrame.SetCurrentPanel(new MainPanel());
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
    //</editor-fold>
    public static String GetCurrentUser(){ return OutputPresenter.GetCurrentUser(); }

    //<editor-fold desc="Setup Comment Components">
    public static void SetupSubmitButton(JButton submitButton, ActionListener actionListener, boolean isEnabled, String toolTip) {
        //todo: add some feed back on clicking the buttons
        submitButton.setBorder(BorderFactory.createLineBorder(BLUE_HAZE));
        submitButton.setPreferredSize(new Dimension(100, 38));
        submitButton.setMaximumSize(new Dimension(100, 38));
        submitButton.setMinimumSize(new Dimension(100, 38));
        submitButton.setBackground(BLUE_BAYOUX);
        submitButton.setForeground(BLUE_HAZE);
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
            separator.setBackground(BLUE_HAZE);
        }
    }
    public static void SetupInputFields(JTextField... inputFields) {
        for (JTextField inputField : inputFields) {
            inputField.setBackground(BLUE_BAYOUX);
            inputField.setForeground(BLUE_HAZE);
            inputField.setBorder(null);
        }
    }
    public static void SetupLogoPanelLayout(JPanel logoPanel, JLabel LogoIconPanel, JLabel LogoTextPanel) {
        var logoPanelLayout = new GroupLayout(logoPanel);

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
                                .addGap(93, 93, 93))
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