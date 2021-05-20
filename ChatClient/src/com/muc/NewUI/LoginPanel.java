package com.muc.NewUI;

import com.muc.ChatClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.muc.NewUI.OutputView.*;
import static javax.swing.GroupLayout.*;

public class LoginPanel extends JPanel implements IPanel, ActionListener {
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
    //</editor-fold>

    public LoginPanel() {
        this.setPreferredSize(new Dimension(900, 500));
        SetupLogoPanel();
        SetupInputPanel();
        SetupMainPanel();
    }

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
                inputPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(inputPanelLayout.createSequentialGroup()
                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addGap(83, 83, 83)
                                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(passwordLabel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(loginLabel))
                                                .addGap(26, 26, 26)
                                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(loginSeparator)
                                                        .addComponent(loginField)
                                                        .addComponent(passwordField)
                                                        .addComponent(passwordSeparator, Alignment.TRAILING, PREFERRED_SIZE, 226, PREFERRED_SIZE)))
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addGap(142, 142, 142)
                                                .addComponent(signInButton, PREFERRED_SIZE, 71, PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(signUpButton, PREFERRED_SIZE, 71, PREFERRED_SIZE)))
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
                                .addGap(29, 29, 29)
                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.TRAILING)
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
    //</editor-fold>

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(signInButton)) OnClick_SignIn(loginField, passwordField);
        //else if (event.getSource().equals(signUpButton)) OnClick_SwapPanels(signUpPanel);
    }



}