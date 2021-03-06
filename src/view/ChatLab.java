
package view;

import controller.ClientController;
import controller.ReceivingFileThread;
import controller.SendFileFrame;
import controller.SendingFileThread;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatLab extends javax.swing.JPanel {

    private final HashMap<String, JLabel> UsernamesJLabelsList = new HashMap<>();
    private final HashMap<String, JLabel> ConversationsJLabelsList = new HashMap<>();
    private final HashMap<String, StyledDocument> ConversationsDocList = new HashMap<>();
    private final String serverHost = "localhost";
    StyledDocument CurrentDoc;
    String FocusedChat = "Group Chat";
    String AttachedFilePath;
    //<editor-fold desc="Vars">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel appLogo;
    private javax.swing.JLabel appName;
    private javax.swing.JButton attachButton;
    private javax.swing.JPanel chatPanel;
    private javax.swing.JLabel chatSectionLabel;
    private javax.swing.JTextPane chatTP = new JTextPane();
    private javax.swing.JScrollPane chatTPScroller;
    private javax.swing.JLabel conversationsSectionLabel;
    private javax.swing.JButton emojiButton;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JTextArea inputArea;
    private javax.swing.JScrollPane inputScroller;
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

    public ChatLab() {
        initComponents();
    }

    private void SetupWelcomePage(String username) {
        AddToConversationsDocList("Welcome", new DefaultStyledDocument());
        StyledDocument doc = new DefaultStyledDocument();
        int len = doc.getLength();

        SimpleAttributeSet MainMessageStyle = new SimpleAttributeSet();
        StyleConstants.setFontFamily(MainMessageStyle, "Source Code Pro");
        StyleConstants.setBold(MainMessageStyle, true);
        StyleConstants.setFontSize(MainMessageStyle, 30);
        StyleConstants.setSpaceAbove(MainMessageStyle, 60);
        StyleConstants.setForeground(MainMessageStyle, new Color(74, 78, 105));
        StyleConstants.setAlignment(MainMessageStyle, StyleConstants.ALIGN_CENTER);

        SimpleAttributeSet SubMessageStyle = new SimpleAttributeSet();
        StyleConstants.setFontFamily(SubMessageStyle, "Source Code Pro");
        StyleConstants.setBold(SubMessageStyle, true);
        StyleConstants.setFontSize(SubMessageStyle, 20);
        StyleConstants.setSpaceAbove(SubMessageStyle, 20);
        StyleConstants.setForeground(SubMessageStyle, new Color(74, 78, 105));
        StyleConstants.setAlignment(SubMessageStyle, StyleConstants.ALIGN_CENTER);

        String MainMessage = "Welcome back, " + username + "\n";
        String SubMessage = "We are super excited to have you onboard !!";
        try {
            doc.insertString(len, MainMessage, MainMessageStyle);
            doc.setParagraphAttributes(len, MainMessage.length(), MainMessageStyle, false);
            doc.insertString(len + MainMessage.length(), SubMessage, SubMessageStyle);
            doc.setParagraphAttributes(len, SubMessage.length(), SubMessageStyle, true);
        } catch (BadLocationException ex) {
            Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
        }


        AddToConversationsDocList("Welcome", doc);
        SetCurrentConversationDoc(ConversationsDocList.get("Welcome"));
    }

    public JTextPane getTpMessage() {
        return chatTP;
    }

    public JTextArea getTaInput() {
        return inputArea;
    }

    public JTextPane getOnlineList() {
        return usersListTP;
    }

    public JLabel getLbRoom() {
        return chatSectionLabel;
    }

    public JButton getBtSend() {
        return sendButton;
    }

    public JLabel getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName.setText(username);
        this.SetupWelcomePage(username);
    }

    public HashMap<String, JLabel> GetUsernamesJLabelsList() {
        return UsernamesJLabelsList;
    }

    public HashMap<String, JLabel> GetConversationsJLabelsList() {
        return ConversationsJLabelsList;
    }

    public HashMap<String, StyledDocument> GetConversationsDocList() {
        return ConversationsDocList;
    }
    //</editor-fold>

    public void AddToConversationsDocList(String name, StyledDocument doc) {
        ConversationsDocList.put(name, doc);
    }

    public StyledDocument GetConversationsDoc(String name) {
        return ConversationsDocList.get(name);
    }

    public StyledDocument GetCurrentConversationDoc() {
        return this.CurrentDoc;
    }

    public void SetCurrentConversationDoc(StyledDocument currentDoc) {
        this.CurrentDoc = currentDoc;
        this.chatTP.setDocument(this.CurrentDoc);
        chatTP.setCaretPosition(this.CurrentDoc.getLength());
    }

    public void SetFocusedChat(String username) {
        this.FocusedChat = username;
    }

    public String GetFocusedChat() {
        return this.FocusedChat;
    }

    public void EnableInput() {
        inputArea.setEditable(true);
        sendButton.setEnabled(true);
        attachButton.setEnabled(true);
        emojiButton.setEnabled(true);
    }

    public void SetRoomName(String clickedUsername) {
        this.chatSectionLabel.setText(clickedUsername);
    }

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
        profilePanel = new javax.swing.JPanel();
        userName = new javax.swing.JLabel();
        userIcon = new javax.swing.JLabel();
        chatPanel = new javax.swing.JPanel();
        inputScroller = new javax.swing.JScrollPane();
        inputArea = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        attachButton = new javax.swing.JButton();
        chatTPScroller = new javax.swing.JScrollPane();
        chatTP = new javax.swing.JTextPane();
        emojiButton = new javax.swing.JButton();
        chatSectionLabel = new javax.swing.JLabel();
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
        pastConversationsTP.setEditable(false);
        pastConversationsScroller.setViewportView(pastConversationsTP);

        javax.swing.GroupLayout pastConversationsPanelLayout = new javax.swing.GroupLayout(pastConversationsPanel);
        pastConversationsPanel.setLayout(pastConversationsPanelLayout);
        pastConversationsPanelLayout.setHorizontalGroup(
                pastConversationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pastConversationsPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pastConversationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(conversationsSectionLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pastConversationsScroller, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        pastConversationsPanelLayout.setVerticalGroup(
                pastConversationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pastConversationsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(conversationsSectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pastConversationsScroller, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                .addGap(70, 70, 70)
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
                                        .addComponent(userName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        inputArea.setEditable(false);
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
        sendButton.setEnabled(false);
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
        attachButton.setEnabled(false);
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
        chatTP.setEditable(false);
        chatTPScroller.setViewportView(chatTP);

        emojiButton.setBackground(new java.awt.Color(76, 96, 133));
        emojiButton.setForeground(new java.awt.Color(244, 252, 231));
        emojiButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ChatApp/happy_45px.png"))); // NOI18N
        emojiButton.setToolTipText("Add an item to the list");
        emojiButton.setBorder(null);
        emojiButton.setContentAreaFilled(false);
        emojiButton.setFocusPainted(false);
        emojiButton.setFocusable(false);
        emojiButton.setEnabled(false);
        emojiButton.setMaximumSize(new java.awt.Dimension(100, 38));
        emojiButton.setMinimumSize(new java.awt.Dimension(100, 38));
        emojiButton.setPreferredSize(new java.awt.Dimension(100, 38));
        emojiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emojiButtonActionPerformed(evt);
            }
        });

        chatSectionLabel.setBackground(new java.awt.Color(154, 140, 152));
        chatSectionLabel.setFont(new java.awt.Font("Source Code Pro", 0, 24)); // NOI18N
        chatSectionLabel.setForeground(new java.awt.Color(242, 233, 228));
        chatSectionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

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
                                                .addComponent(emojiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                                .addComponent(emojiButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
        usersListTP.setEditable(false);
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
                                .addComponent(usersListScroller, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    public void appendMessage_Received(String msg1, String msg2, Color c1, Color c2) {
        //int len = chatTP.getDocument().getLength();
        int len = GetCurrentConversationDoc().getLength();

        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setFontFamily(sas, "Serif");
        StyleConstants.setBold(sas, true);
        StyleConstants.setFontSize(sas, 14);
        StyleConstants.setForeground(sas, c1);
        StyleConstants.setAlignment(sas, StyleConstants.ALIGN_LEFT);

        try {
            GetCurrentConversationDoc().insertString(len, msg1, sas);
            GetCurrentConversationDoc().setParagraphAttributes(len, msg1.length(), sas, false);
        } catch (BadLocationException ex) {
            Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        len = len + msg1.length();
        sas = new SimpleAttributeSet();
        StyleConstants.setFontFamily(sas, "Arial");
        StyleConstants.setFontSize(sas, 14);
        StyleConstants.setForeground(sas, c2);

        try {
            GetCurrentConversationDoc().insertString(len, msg2 + "\n", sas);
        } catch (BadLocationException ex) {
            Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void appendMessage_Sent(String msg1, String msg2, Color c1, Color c2) {
        int len = GetCurrentConversationDoc().getLength();

        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setFontFamily(sas, "Serif");
        StyleConstants.setBold(sas, true);
        StyleConstants.setFontSize(sas, 14);
        StyleConstants.setForeground(sas, c1);
        StyleConstants.setAlignment(sas, StyleConstants.ALIGN_RIGHT);

        try {
            GetCurrentConversationDoc().insertString(len, msg1, sas);
            GetCurrentConversationDoc().setParagraphAttributes(len, msg1.length(), sas, false);
        } catch (BadLocationException ex) {
            Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        len = len + msg1.length();
        sas = new SimpleAttributeSet();
        StyleConstants.setFontFamily(sas, "Arial");
        StyleConstants.setFontSize(sas, 14);
        StyleConstants.setForeground(sas, c2);

        try {
            GetCurrentConversationDoc().insertString(len, msg2 + "\n", sas);
        } catch (BadLocationException ex) {
            Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void appendMessage_Alert(String message, Color color) {
        int len = GetConversationsDoc("Group Chat").getLength();

        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setFontFamily(sas, "Comic Sans MS");
        StyleConstants.setItalic(sas, true);
        StyleConstants.setFontSize(sas, 14);
        StyleConstants.setForeground(sas, color);
        StyleConstants.setAlignment(sas, StyleConstants.ALIGN_CENTER);

        try {
            GetConversationsDoc("Group Chat").insertString(len, message + "\n", sas);
            GetConversationsDoc("Group Chat").setParagraphAttributes(len, message.length(), sas, false);
        } catch (BadLocationException ex) {
            Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void appendMessage_OnlineUsersList(String userName, Color color, ClientController clientControllerInstance) {
        int len = usersListTP.getDocument().getLength();
        StyledDocument doc = usersListTP.getStyledDocument();

        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setFontFamily(sas, "Comic Sans MS");
        StyleConstants.setBold(sas, true);
        StyleConstants.setFontSize(sas, 23);
        StyleConstants.setForeground(sas, color);

        JLabel userNameLabel = new JLabel(userName);
        userNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        userNameLabel.setForeground(color);
        userNameLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clientControllerInstance.openPrivateChatInsideRoom(userName);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                userNameLabel.setBackground(Color.lightGray);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                userNameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                userNameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
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

    public void appendMessage_ConversationsList(String ConversationName, String iconPath, ClientController clientControllerInstance) {
        int len = pastConversationsTP.getDocument().getLength();
        StyledDocument doc = pastConversationsTP.getStyledDocument();
        SetFocusedChat(ConversationName);

        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setFontFamily(sas, "Comic Sans MS");
        StyleConstants.setBold(sas, true);
        StyleConstants.setFontSize(sas, 23);
        StyleConstants.setAlignment(sas, 10);

        JLabel conversationLabel = new JLabel(ConversationName);
        conversationLabel.setFont(new Font("Source Code Pro", Font.BOLD, 20)); // NOI18N
        conversationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        conversationLabel.setIcon(new ImageIcon(getClass().getResource(iconPath))); // NOI18N
        conversationLabel.setSize(new Dimension(183, 50));

        //todo: highlight the pressed chat
        conversationLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clientControllerInstance.openPrivateChatInsideRoom(ConversationName);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                conversationLabel.setBackground(Color.lightGray);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                conversationLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                conversationLabel.setBackground(new Color(150, 153, 172));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                conversationLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                conversationLabel.setBackground(null);
            }
        });
        try {
            StyleConstants.setComponent(sas, conversationLabel);
            doc.insertString(len, "Ignored\n\n", sas);
        } catch (BadLocationException ex) {
            Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        len = usersListTP.getDocument().getLength();
        usersListTP.setCaretPosition(len);
    }

    private void openSendFileFrame() {
        SendFileFrame sendFileFrame = new SendFileFrame(serverHost, userName.getText());

        sendFileFrame.thePersonIamChattingWith = FocusedChat;
        sendFileFrame.getTfReceiver().setText(FocusedChat);
        sendFileFrame.setVisible(true);
        sendFileFrame.setLocation(450, 250);
        sendFileFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void displayOpenDialog() {
        JFileChooser chooser = new JFileChooser();
        int kq = chooser.showOpenDialog(this);
        if (kq == JFileChooser.APPROVE_OPTION) {
            AttachedFilePath = chooser.getSelectedFile().getAbsolutePath();
            SendSelectedFile();
        } else AttachedFilePath = "";
    }

    private void SendSelectedFile() {
        try {
            Socket socketOfSender = new Socket(serverHost, 9999);
            new SendingFileThread(userName.getText(), FocusedChat, AttachedFilePath, socketOfSender, null, null).start();
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertButton(String sender, String fileName) {
        int len = GetCurrentConversationDoc().getLength();

        JButton downloadButton = new JButton(fileName);
        downloadButton.setName(fileName);
        downloadButton.addActionListener(ae -> downloadFile(fileName));

        SimpleAttributeSet sas = new SimpleAttributeSet();

        if (sender.equals(this.userName.getText())) {
            StyleConstants.setAlignment(sas, StyleConstants.ALIGN_RIGHT);
            appendMessage_Sent(sender, " sends a file ", new Color(33, 72, 127), Color.black);
        } else {
            StyleConstants.setAlignment(sas, StyleConstants.ALIGN_LEFT);
            appendMessage_Received(sender, " sends a file ", new Color(33, 72, 127), Color.black);
        }

        try {
            StyleConstants.setComponent(sas, downloadButton);
            GetCurrentConversationDoc().insertString(len, "Ignored\n\n", sas);
        } catch (BadLocationException ex) {
            Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void downloadFile(String buttonName) {
        String myDownloadFolder;
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int kq = chooser.showSaveDialog(this);
        if (kq == JFileChooser.APPROVE_OPTION) {
            myDownloadFolder = chooser.getSelectedFile().getAbsolutePath();
        } else {
            myDownloadFolder = "D:";
            JOptionPane.showMessageDialog(this, "The default folder to save file is in D:\\", "Notice", JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            Socket socketOfReceiver = new Socket(serverHost, 9999);
            new ReceivingFileThread(socketOfReceiver, myDownloadFolder, buttonName, null).start();
        } catch (IOException ex) {
            Logger.getLogger(PrivateChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //<editor-fold desc="Events">
    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void profilePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePanelMouseClicked
    }//GEN-LAST:event_profilePanelMouseClicked

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
    }//GEN-LAST:event_sendButtonActionPerformed

    private void attachButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attachButtonActionPerformed
        displayOpenDialog();
    }//GEN-LAST:event_attachButtonActionPerformed

    private void emojiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emojiButtonActionPerformed
    }//GEN-LAST:event_emojiButtonActionPerformed
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
