package com.muc.NewUI;

import com.muc.ChatClient;
import com.muc.MessageGrpPane;
import com.muc.UserListPane;
import com.muc.UserPaneOffLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static com.muc.NewUI.OutputView.*;

public class MainPanel extends JPanel implements IPanel, ActionListener {
    private final JPanel headerPanel = new JPanel();
    private final JPanel profilePanel = new JPanel();
    private final JLabel appLogo = new JLabel(new ImageIcon("resources/Images/chat_bubble_100px.png"));
    private final JLabel appName = new JLabel("BreadCost");
    private final JLabel currentUserLabel = new JLabel("Greeting !");
    private final JLabel userName = new JLabel("Oknaa");
    private final JLabel userIcon = new JLabel(new ImageIcon("resources/Images/male_user_100px.png"));
    private final JButton logoutButton = new JButton(new ImageIcon("resources/Images/sign_out_70px.png"));


    MainPanel(ChatClient client, String strLogin) {
        UserListPane onlineListPanel = new UserListPane(client, strLogin);
        MessageGrpPane groupChatPanel = new MessageGrpPane(client, strLogin);
        UserPaneOffLine offlineListPanel = new UserPaneOffLine(client, strLogin);

        setPreferredSize(new Dimension(900, 500));
        setBackground(INDEPENDENCE);
        SetupHeaderPanel();
        SetupProfilePanel(strLogin);


        add(headerPanel, BorderLayout.NORTH);
        add(profilePanel, BorderLayout.NORTH);
        add(onlineListPanel, BorderLayout.WEST);
        add(groupChatPanel, BorderLayout.CENTER);
        add(offlineListPanel, BorderLayout.EAST);
    }
    private void SetupHeaderPanel() {
        headerPanel.setBackground(INDEPENDENCE);
        //headerPanel.setPreferredSize(new Dimension(450, 500));
        appName.setFont(new Font("Source Code Pro", Font.PLAIN, 48));
        appName.setForeground(ISABELLINE);
        SetupHeaderPanelLayout();
    }
    private void SetupHeaderPanelLayout() {
        GroupLayout headerPanelLayout = new GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(headerPanelLayout.createSequentialGroup()
                        .addComponent(appLogo)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(appName)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )
        );
        headerPanelLayout.setVerticalGroup(headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(headerPanelLayout.createSequentialGroup()
                        .addComponent(appLogo)
                        .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(headerPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(appName, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )
        );
    }

    private void SetupProfilePanel(String strLogin) {
        profilePanel.setBackground(INDEPENDENCE);
        SetupUserName(strLogin);
        SetupSubmitButton(logoutButton, this, true, "Logout");
        SetupProfilePanelLayout();
    }
    private void SetupUserName(String strLogin) {
        currentUserLabel.setFont(new Font("Source Code Pro", Font.PLAIN, 16)); // NOI18N
        currentUserLabel.setForeground(ISABELLINE);
        userName.setPreferredSize(new Dimension(200, 46));
        userName.setFont(new Font("Source Code Pro", Font.PLAIN, 36));
        userName.setForeground(ISABELLINE);
        userName.setText(strLogin);
    }
    private void SetupProfilePanelLayout() {
        GroupLayout profilePanelLayout = new GroupLayout(profilePanel);
        profilePanel.setLayout(profilePanelLayout);
        profilePanelLayout.setHorizontalGroup(
                profilePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(profilePanelLayout.createSequentialGroup()
                                .addComponent(userIcon)
                                .addGap(22, 22, 22)
                                .addGroup(profilePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(currentUserLabel)
                                        .addGroup(profilePanelLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(userName)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                                .addComponent(logoutButton, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        profilePanelLayout.setVerticalGroup(
                profilePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, profilePanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(userIcon))
                        .addGroup(profilePanelLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(currentUserLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userName)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, profilePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(logoutButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(logoutButton)) OnClick_Logout();
    }
}
