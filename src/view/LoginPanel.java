/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Oknaa
 */
public class LoginPanel extends javax.swing.JPanel {

    /**
     * Creates new form LoginPanel
     */
    public LoginPanel() {
        initComponents();
    }

    public JButton getBtOK() {
        return btOK;
    }
    public JButton GetSignUpButton() {
        return signUpButton;
    }
    public JTextField getTfNickname() {
        return loginField2;
    }
    public JTextField getTfPass() {
        return tfPass;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        logoPanel = new javax.swing.JPanel();
        appLogo = new javax.swing.JLabel();
        appName = new javax.swing.JLabel();
        inputPanel = new javax.swing.JPanel();
        loginField2 = new javax.swing.JTextField();
        loginLabel = new javax.swing.JLabel();
        loginSeparator = new javax.swing.JSeparator();
        tfPass = new javax.swing.JPasswordField();
        passwordLabel = new javax.swing.JLabel();
        passwordSeparator = new javax.swing.JSeparator();
        btOK = new javax.swing.JButton();
        signUpButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(900, 500));

        jPanel1.setEnabled(false);

        logoPanel.setBackground(new java.awt.Color(74, 78, 105));

        appLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ChatApp/chat_bubble_127px.png"))); // NOI18N

        appName.setFont(new java.awt.Font("Source Code Pro", 0, 48)); // NOI18N
        appName.setForeground(new java.awt.Color(242, 233, 228));
        appName.setText("BreadCost");

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addGroup(logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(logoPanelLayout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(appLogo))
                    .addGroup(logoPanelLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(appName)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        logoPanelLayout.setVerticalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addContainerGap(133, Short.MAX_VALUE)
                .addComponent(appLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(appName, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(160, 160, 160))
        );

        inputPanel.setBackground(new java.awt.Color(154, 140, 152));
        inputPanel.setPreferredSize(new java.awt.Dimension(450, 500));
        inputPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputPanelMouseClicked(evt);
            }
        });

        loginField2.setBackground(new java.awt.Color(154, 140, 152));
        loginField2.setForeground(new java.awt.Color(244, 252, 231));
        loginField2.setText("username");
        loginField2.setBorder(null);
        loginField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                loginField2FocusGained(evt);
            }
        });
        loginField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginField2ActionPerformed(evt);
            }
        });

        loginLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ChatApp/name_45px.png"))); // NOI18N

        loginSeparator.setBackground(new java.awt.Color(244, 252, 231));

        tfPass.setBackground(new java.awt.Color(154, 140, 152));
        tfPass.setForeground(new java.awt.Color(244, 252, 231));
        tfPass.setText("password");
        tfPass.setBorder(null);
        tfPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfPassFocusGained(evt);
            }
        });

        passwordLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ChatApp/lock_45px.png"))); // NOI18N

        passwordSeparator.setBackground(new java.awt.Color(244, 252, 231));

        btOK.setBackground(new java.awt.Color(76, 96, 133));
        btOK.setForeground(new java.awt.Color(244, 252, 231));
        btOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ChatApp/login_80px.png"))); // NOI18N
        btOK.setToolTipText("Login");
        btOK.setBorder(null);
        btOK.setContentAreaFilled(false);
        btOK.setFocusPainted(false);
        btOK.setFocusable(false);
        btOK.setMaximumSize(new java.awt.Dimension(100, 38));
        btOK.setMinimumSize(new java.awt.Dimension(100, 38));
        btOK.setPreferredSize(new java.awt.Dimension(100, 38));
        btOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOKActionPerformed(evt);
            }
        });

        signUpButton.setBackground(new java.awt.Color(76, 96, 133));
        signUpButton.setForeground(new java.awt.Color(244, 252, 231));
        signUpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ChatApp/add_80px.png"))); // NOI18N
        signUpButton.setToolTipText("Signup");
        signUpButton.setBorder(null);
        signUpButton.setContentAreaFilled(false);
        signUpButton.setFocusPainted(false);
        signUpButton.setFocusable(false);
        signUpButton.setMaximumSize(new java.awt.Dimension(100, 38));
        signUpButton.setMinimumSize(new java.awt.Dimension(100, 38));
        signUpButton.setPreferredSize(new java.awt.Dimension(100, 38));
        signUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inputPanelLayout = new javax.swing.GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(loginLabel))
                        .addGap(26, 26, 26)
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(loginSeparator)
                            .addComponent(loginField2)
                            .addComponent(tfPass)
                            .addComponent(passwordSeparator, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(btOK, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(signUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        inputPanelLayout.setVerticalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addComponent(loginField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(loginSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(loginLabel))
                .addGap(24, 24, 24)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addComponent(tfPass, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(passwordSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(passwordLabel))
                .addGap(29, 29, 29)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(signUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btOK, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(160, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loginField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginField2FocusGained
        // TODO add your handling code here:
        loginField2.setText("");
    }//GEN-LAST:event_loginField2FocusGained

    private void loginField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginField2ActionPerformed

    private void tfPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfPassFocusGained
        // TODO add your handling code here:
        tfPass.setText("");
    }//GEN-LAST:event_tfPassFocusGained

    private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_signUpButtonActionPerformed

    private void inputPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputPanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_inputPanelMouseClicked

    private void btOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btOKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel appLogo;
    private javax.swing.JLabel appName;
    private javax.swing.JButton btOK;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField loginField2;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JSeparator loginSeparator;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JSeparator passwordSeparator;
    private javax.swing.JButton signUpButton;
    private javax.swing.JPasswordField tfPass;
    // End of variables declaration//GEN-END:variables
}
