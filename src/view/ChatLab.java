/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import controller.ClientFrame;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oknaa
 */
public class ChatLab extends javax.swing.JPanel {

    private final HashMap<String, JLabel> UsernamesJLabelsList = new HashMap<>();
    StyledDocument doc = new DefaultStyledDocument();


    public JTextPane getTpMessage() {
        return chatTP;
    }
    public JTextArea getTaInput() {
        return inputArea;
    }
    public JTextPane getOnlineList() { return usersListTP; }
    public HashMap<String, JLabel> GetUsernamesJLabelsList() { return UsernamesJLabelsList; }
    public JLabel getLbRoom() { return chatSectionLabel; }
    public JButton getBtExit() {
        return logoutButton;
    }
    public JButton getBtSend() { return sendButton; }
    public JLabel getUserName() { return userName; }

    /**
     * Creates new form testLoginPanel
     */
    public ChatLab() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        appLogo = new javax.swing.JLabel();
        appName = new javax.swing.JLabel();
        pastConversationsPanel = new javax.swing.JPanel();
        conversationsSectionLabel = new javax.swing.JLabel();
        pastConversationsScroller = new javax.swing.JScrollPane();
        pastConversationsTP = new javax.swing.JTextPane();
        addGroupButton = new javax.swing.JButton();
        profilePanel = new javax.swing.JPanel();
        userName = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        userIcon = new javax.swing.JLabel();
        chatPanel = new javax.swing.JPanel();
        inputScroller = new javax.swing.JScrollPane();
        inputArea = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        attachButton = new javax.swing.JButton();
        chatTPScroller = new javax.swing.JScrollPane();
        chatTP = new javax.swing.JTextPane();
        chatSectionLabel = new javax.swing.JLabel();
        attachButton1 = new javax.swing.JButton();
        usersPanel = new javax.swing.JPanel();
        usersListScroller = new javax.swing.JScrollPane();
        usersListTP = new javax.swing.JTextPane();
        usersSectionLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(74, 78, 105));
        setPreferredSize(new java.awt.Dimension(848, 507));

        headerPanel.setBackground(new java.awt.Color(74, 78, 105));
        headerPanel.setForeground(new java.awt.Color(5, 5, 5));
        headerPanel.setPreferredSize(new java.awt.Dimension(450, 500));

        appLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ChatApp/chat_bubble_100px.png"))); // NOI18N

        appName.setFont(new java.awt.Font("Source Code Pro", 0, 36)); // NOI18N
        appName.setForeground(new java.awt.Color(242, 233, 228));
        appName.setText("BreadCost");

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addComponent(appLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(appName)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addComponent(appLogo)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(appName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pastConversationsPanel.setBackground(new java.awt.Color(74, 78, 105));

        conversationsSectionLabel.setFont(new java.awt.Font("Source Code Pro", 0, 24)); // NOI18N
        conversationsSectionLabel.setForeground(new java.awt.Color(242, 233, 228));
        conversationsSectionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        conversationsSectionLabel.setText("Conversations");

        pastConversationsTP.setBackground(new java.awt.Color(154, 140, 152));
        pastConversationsTP.setForeground(new java.awt.Color(242, 233, 228));
        pastConversationsScroller.setViewportView(pastConversationsTP);

        addGroupButton.setBackground(new java.awt.Color(76, 96, 133));
        addGroupButton.setForeground(new java.awt.Color(244, 252, 231));
        addGroupButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ChatApp/add_80px.png"))); // NOI18N
        addGroupButton.setToolTipText("Add an item to the list");
        addGroupButton.setBorder(null);
        addGroupButton.setContentAreaFilled(false);
        addGroupButton.setFocusPainted(false);
        addGroupButton.setFocusable(false);
        addGroupButton.setMaximumSize(new java.awt.Dimension(100, 38));
        addGroupButton.setMinimumSize(new java.awt.Dimension(100, 38));
        addGroupButton.setPreferredSize(new java.awt.Dimension(100, 38));
        addGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGroupButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pastConversationsPanelLayout = new javax.swing.GroupLayout(pastConversationsPanel);
        pastConversationsPanel.setLayout(pastConversationsPanelLayout);
        pastConversationsPanelLayout.setHorizontalGroup(
            pastConversationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pastConversationsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pastConversationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pastConversationsPanelLayout.createSequentialGroup()
                        .addGroup(pastConversationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(conversationsSectionLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pastConversationsScroller, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pastConversationsPanelLayout.createSequentialGroup()
                        .addComponent(addGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))))
        );
        pastConversationsPanelLayout.setVerticalGroup(
            pastConversationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pastConversationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(conversationsSectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pastConversationsScroller, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        profilePanel.setBackground(new java.awt.Color(74, 78, 105));
        profilePanel.setPreferredSize(new java.awt.Dimension(450, 500));
        profilePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profilePanelMouseClicked(evt);
            }
        });

        userName.setFont(new java.awt.Font("Source Code Pro", 0, 36)); // NOI18N
        userName.setForeground(new java.awt.Color(244, 252, 231));
        userName.setText("Oknaa");

        logoutButton.setBackground(new java.awt.Color(76, 96, 133));
        logoutButton.setForeground(new java.awt.Color(244, 252, 231));
        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ChatApp/sign_out_70px.png"))); // NOI18N
        logoutButton.setToolTipText("Logout");
        logoutButton.setBorder(null);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setFocusable(false);
        logoutButton.setMaximumSize(new java.awt.Dimension(100, 38));
        logoutButton.setMinimumSize(new java.awt.Dimension(100, 38));
        logoutButton.setPreferredSize(new java.awt.Dimension(100, 38));
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        userIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ChatApp/male_user_100px.png"))); // NOI18N

        javax.swing.GroupLayout profilePanelLayout = new javax.swing.GroupLayout(profilePanel);
        profilePanel.setLayout(profilePanelLayout);
        profilePanelLayout.setHorizontalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilePanelLayout.createSequentialGroup()
                .addComponent(userIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        profilePanelLayout.setVerticalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, profilePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(userIcon))
            .addGroup(profilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        chatPanel.setBackground(new java.awt.Color(74, 78, 105));
        chatPanel.setForeground(new java.awt.Color(5, 5, 5));
        chatPanel.setPreferredSize(new java.awt.Dimension(450, 500));

        inputArea.setBackground(new java.awt.Color(74, 78, 105));
        inputArea.setColumns(20);
        inputArea.setFont(new java.awt.Font("Source Code Pro", 0, 18)); // NOI18N
        inputArea.setForeground(new java.awt.Color(244, 252, 231));
        inputArea.setTabSize(0);
        inputArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(154, 140, 152), 2));
        inputArea.setMargin(new java.awt.Insets(12, 12, 2, 2));
        inputScroller.setViewportView(inputArea);

        sendButton.setBackground(new java.awt.Color(76, 96, 133));
        sendButton.setForeground(new java.awt.Color(244, 252, 231));
        sendButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ChatApp/paper_plane_45px.png"))); // NOI18N
        sendButton.setToolTipText("Add an item to the list");
        sendButton.setBorder(null);
        sendButton.setContentAreaFilled(false);
        sendButton.setFocusPainted(false);
        sendButton.setFocusable(false);
        sendButton.setMaximumSize(new java.awt.Dimension(100, 38));
        sendButton.setMinimumSize(new java.awt.Dimension(100, 38));
        sendButton.setPreferredSize(new java.awt.Dimension(100, 38));
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        attachButton.setBackground(new java.awt.Color(76, 96, 133));
        attachButton.setForeground(new java.awt.Color(244, 252, 231));
        attachButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ChatApp/attach_45px.png"))); // NOI18N
        attachButton.setToolTipText("Add an item to the list");
        attachButton.setBorder(null);
        attachButton.setContentAreaFilled(false);
        attachButton.setFocusPainted(false);
        attachButton.setFocusable(false);
        attachButton.setMaximumSize(new java.awt.Dimension(100, 38));
        attachButton.setMinimumSize(new java.awt.Dimension(100, 38));
        attachButton.setPreferredSize(new java.awt.Dimension(100, 38));
        attachButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attachButtonActionPerformed(evt);
            }
        });

        chatTP.setBackground(new java.awt.Color(154, 140, 152));
        chatTP.setForeground(new java.awt.Color(242, 233, 228));
        chatTPScroller.setViewportView(chatTP);

        chatSectionLabel.setBackground(new java.awt.Color(154, 140, 152));
        chatSectionLabel.setFont(new java.awt.Font("Source Code Pro", 0, 24)); // NOI18N
        chatSectionLabel.setForeground(new java.awt.Color(242, 233, 228));
        chatSectionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chatSectionLabel.setText("Room 1");

        attachButton1.setBackground(new java.awt.Color(76, 96, 133));
        attachButton1.setForeground(new java.awt.Color(244, 252, 231));
        attachButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ChatApp/happy_45px.png"))); // NOI18N
        attachButton1.setToolTipText("Add an item to the list");
        attachButton1.setBorder(null);
        attachButton1.setContentAreaFilled(false);
        attachButton1.setFocusPainted(false);
        attachButton1.setFocusable(false);
        attachButton1.setMaximumSize(new java.awt.Dimension(100, 38));
        attachButton1.setMinimumSize(new java.awt.Dimension(100, 38));
        attachButton1.setPreferredSize(new java.awt.Dimension(100, 38));
        attachButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attachButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout chatPanelLayout = new javax.swing.GroupLayout(chatPanel);
        chatPanel.setLayout(chatPanelLayout);
        chatPanelLayout.setHorizontalGroup(
            chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatPanelLayout.createSequentialGroup()
                        .addComponent(inputScroller, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(attachButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(attachButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chatTPScroller))
                .addContainerGap())
            .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(chatSectionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        chatPanelLayout.setVerticalGroup(
            chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatPanelLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(chatTPScroller, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputScroller, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(attachButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(attachButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(26, 26, 26))
            .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(chatPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(chatSectionLabel)
                    .addContainerGap(363, Short.MAX_VALUE)))
        );

        usersPanel.setBackground(new java.awt.Color(74, 78, 105));

        usersListTP.setBackground(new java.awt.Color(154, 140, 152));
        usersListTP.setForeground(new java.awt.Color(242, 233, 228));
        usersListScroller.setViewportView(usersListTP);

        usersSectionLabel.setFont(new java.awt.Font("Source Code Pro", 0, 24)); // NOI18N
        usersSectionLabel.setForeground(new java.awt.Color(242, 233, 228));
        usersSectionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usersSectionLabel.setText("Users");

        javax.swing.GroupLayout usersPanelLayout = new javax.swing.GroupLayout(usersPanel);
        usersPanel.setLayout(usersPanelLayout);
        usersPanelLayout.setHorizontalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usersSectionLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usersListScroller, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        usersPanelLayout.setVerticalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(usersSectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usersListScroller, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pastConversationsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(chatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(usersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(profilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chatPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                            .addComponent(pastConversationsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(usersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents


    public void appendMessage(String msg1, String msg2, Color c1, Color c2) {
        //int len = chatTP.getDocument().getLength();

        int len = doc.getLength();

        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setFontFamily(sas, "Serif");
        StyleConstants.setBold(sas, true);
        StyleConstants.setFontSize(sas, 14);
        StyleConstants.setForeground(sas, c1);

        try {
            doc.insertString(len, msg1, sas);
        } catch (BadLocationException ex) {
            Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
        }


        len = len + msg1.length();

        sas = new SimpleAttributeSet();
        StyleConstants.setFontFamily(sas, "Arial");
        StyleConstants.setFontSize(sas, 14);
        StyleConstants.setForeground(sas, c2);

        try {
            doc.insertString(len, msg2+"\n", sas);
        } catch (BadLocationException ex) {
            Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        chatTP.setDocument(doc);
        chatTP.setCaretPosition(len+msg2.length());
    }
    public void appendAlertMessage(String message, Color color) {
        //int len = chatTP.getDocument().getLength();
        //StyledDocument doc = (StyledDocument) chatTP.getDocument();
        int len = doc.getLength();

        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setFontFamily(sas, "Comic Sans MS");
        StyleConstants.setItalic(sas, true);
        StyleConstants.setFontSize(sas, 14);
        StyleConstants.setForeground(sas, color);

        try {
            doc.insertString(len, message+"\n", sas);
        } catch (BadLocationException ex) {
            Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        chatTP.setDocument(doc);
        chatTP.setCaretPosition(len+message.length());
    }

    public void appendMessage_OnlineUsers(String userName, Color color, ClientFrame clientFrameInstance) {
        int len = usersListTP.getDocument().getLength();
        StyledDocument doc = usersListTP.getStyledDocument();

        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setFontFamily(sas, "Comic Sans MS");
        StyleConstants.setBold(sas, true);
        StyleConstants.setFontSize(sas, 14);
        StyleConstants.setForeground(sas, color);

        JLabel userNameLabel = new JLabel(userName);

        userNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        userNameLabel.setForeground(color);
        userNameLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clientFrameInstance.openPrivateChatInsideRoom(userName);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                userNameLabel.setBackground(Color.lightGray);
            }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { userNameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); }
            @Override
            public void mouseExited(MouseEvent e) { userNameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); }
        });
        try {
            StyleConstants.setComponent(sas, userNameLabel);
            doc.insertString(len, "Ignored\n", sas);
        } catch (BadLocationException ex) {
            Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        len = usersListTP.getDocument().getLength();
        usersListTP.setCaretPosition(len);
    }


    //<editor-fold desc="Events">
    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void profilePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePanelMouseClicked
    }//GEN-LAST:event_profilePanelMouseClicked

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
    }//GEN-LAST:event_sendButtonActionPerformed

    private void attachButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attachButtonActionPerformed
    }//GEN-LAST:event_attachButtonActionPerformed

    private void addGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGroupButtonActionPerformed
    }//GEN-LAST:event_addGroupButtonActionPerformed

    private void attachButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attachButton1ActionPerformed
    }//GEN-LAST:event_attachButton1ActionPerformed
    //</editor-fold>


    //<editor-fold desc="Vars">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addGroupButton;
    private javax.swing.JLabel appLogo;
    private javax.swing.JLabel appName;
    private javax.swing.JButton attachButton;
    private javax.swing.JButton attachButton1;
    private javax.swing.JPanel chatPanel;
    private javax.swing.JLabel chatSectionLabel;
    private javax.swing.JTextPane chatTP;
    private javax.swing.JScrollPane chatTPScroller;
    private javax.swing.JLabel conversationsSectionLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JTextArea inputArea;
    private javax.swing.JScrollPane inputScroller;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel pastConversationsPanel;
    private javax.swing.JScrollPane pastConversationsScroller;
    private javax.swing.JTextPane pastConversationsTP;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JButton sendButton;
    private javax.swing.JLabel userIcon;
    private javax.swing.JLabel userName;
    private javax.swing.JScrollPane usersListScroller;
    private javax.swing.JTextPane usersListTP;
    private javax.swing.JPanel usersPanel;
    private javax.swing.JLabel usersSectionLabel;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
