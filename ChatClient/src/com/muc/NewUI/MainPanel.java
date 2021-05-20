package com.muc.NewUI;

import com.muc.ChatClient;
import com.muc.MessageGrpPane;
import com.muc.UserListPane;
import com.muc.UserPaneOffLine;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements IPanel {

    MainPanel(ChatClient client, String strLogin) {
        UserListPane onlineListPanel = new UserListPane(client, strLogin);
        MessageGrpPane groupChatPanel = new MessageGrpPane(client, strLogin);
        UserPaneOffLine offlineListPanel = new UserPaneOffLine(client, strLogin);
        JPanel header = new JPanel();
        header.setBackground(Color.green);
        header.setPreferredSize(new Dimension(900, 100));

        setPreferredSize(new Dimension(900, 500));
        /*JFrame frame = new JFrame("BreadCost - " + strLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.setSize(new Dimension(600, 400));
        frame.setVisible(true);
        frame.setResizable(false);
         */

        add(header, BorderLayout.NORTH);
        add(onlineListPanel, BorderLayout.WEST);
        add(groupChatPanel, BorderLayout.CENTER);
        add(offlineListPanel, BorderLayout.EAST);
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
}
