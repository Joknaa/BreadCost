package com.muc.Views.UI;

import static javax.swing.GroupLayout.*;
import static com.muc.Views.OutputView.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SignupPanel extends JPanel implements IPanel, ActionListener, KeyListener {
    //<editor-fold desc="Variables Declarations">">
    private final JPanel logoPanel = new JPanel();
    private final JPanel inputPanel = new JPanel();
    private final JSeparator loginSeparator = new JSeparator();
    private final JSeparator passwordSeparator = new JSeparator();
    private final JSeparator passwordRepeatSeparator = new JSeparator();
    private final JLabel appLogo = new JLabel(new ImageIcon("resources/Images/chat_bubble_127px.png"));
    private final JLabel passwordLabel = new JLabel(new ImageIcon("resources/Images/password_45px.png"));
    private final JLabel loginLabel = new JLabel(new ImageIcon("resources/Images/login_45px.png"));
    private final JLabel appName = new JLabel("Chat Lab");
    private final JLabel passwordRepeatLabel = new JLabel(new ImageIcon("resources/Images/password_45px.png"));
    private final JPasswordField passwordField = new JPasswordField();
    private final JPasswordField passwordRepeatField = new JPasswordField();
    private final JTextField loginField = new JTextField();
    private final JButton signUpButton = new JButton(new ImageIcon("resources/Images/add_80px.png"));
    private final JButton backButton = new JButton(new ImageIcon("resources/Images/back_arrow_50px.png"));

    //</editor-fold>

    public SignupPanel() {
        this.setPreferredSize(new Dimension(900, 500));

        SetupLogoPanel();
        SetupInputPanel();
        SetupMainPanel();
    }

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
        SetupInputFields(this, loginField, passwordField, passwordRepeatField);
        SetupSeparators(loginSeparator, passwordSeparator, passwordRepeatSeparator);
        SetupSubmitButton(signUpButton, this, true,"Click to creat an account");
        SetupSubmitButton(backButton, this, true,"Back");
        SetupInputPanelLayout();
    }
    private void SetupInputPanelLayout() {
        GroupLayout inputPanelLayout = new GroupLayout(inputPanel);

        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
                inputPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, inputPanelLayout.createSequentialGroup()
                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(signUpButton, PREFERRED_SIZE, 71, PREFERRED_SIZE)
                                .addGap(189, 189, 189))
                        .addGroup(inputPanelLayout.createSequentialGroup()
                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addGap(83, 83, 83)
                                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.LEADING, false)
                                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                                .addComponent(passwordRepeatLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.LEADING, false)
                                                                        .addComponent(passwordRepeatField)
                                                                        .addComponent(passwordRepeatSeparator, Alignment.TRAILING, PREFERRED_SIZE, 226, PREFERRED_SIZE)))
                                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.LEADING, false)
                                                                        .addComponent(passwordLabel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(loginLabel))
                                                                .addGap(26, 26, 26)
                                                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.LEADING, false)
                                                                        .addComponent(loginSeparator)
                                                                        .addComponent(loginField)
                                                                        .addComponent(passwordField)
                                                                        .addComponent(passwordSeparator, Alignment.TRAILING, PREFERRED_SIZE, 226, PREFERRED_SIZE)))))
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addGap(203, 203, 203)
                                                .addComponent(backButton, PREFERRED_SIZE, 44, PREFERRED_SIZE)))
                                .addContainerGap(73, Short.MAX_VALUE))
        );
        inputPanelLayout.setVerticalGroup(
                inputPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(inputPanelLayout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addComponent(loginField, PREFERRED_SIZE, 40, PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(loginSeparator, PREFERRED_SIZE, 10, PREFERRED_SIZE))
                                        .addComponent(loginLabel))
                                .addGap(24, 24, 24)
                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addComponent(passwordField, PREFERRED_SIZE, 39, PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(passwordSeparator, PREFERRED_SIZE, 13, PREFERRED_SIZE))
                                        .addComponent(passwordLabel))
                                .addGap(24, 24, 24)
                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addComponent(passwordRepeatField, PREFERRED_SIZE, 39, PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(passwordRepeatSeparator, PREFERRED_SIZE, 13, PREFERRED_SIZE))
                                        .addComponent(passwordRepeatLabel))
                                .addGap(18, 18, 18)
                                .addComponent(signUpButton, PREFERRED_SIZE, 71, PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addComponent(backButton, PREFERRED_SIZE, 46, PREFERRED_SIZE)
                                .addContainerGap())
        );
    }

    private void SetupMainPanel() {
        SetupMainPanelLayout(logoPanel, inputPanel, this);
    }

    @Override
    public JPanel GetPanel() { return this; }
    @Override
    public void Activate(){ this.setVisible(true);}
    @Override
    public void Deactivate(){ this.setVisible(false);}
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(signUpButton)) OnClick_SignUp(loginField, passwordField, passwordRepeatField);
        else if (e.getSource().equals(backButton)) OnClick_SwapPanels(loginPanel);
    }

    @Override
    public void keyTyped(KeyEvent e) { //todo; action onpressing ENTER
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            OnClick_SignUp(loginField, passwordField, passwordRepeatField);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}