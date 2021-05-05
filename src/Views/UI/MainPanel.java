package Views.UI;

import Presenters.Client.ChatClient;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import static Views.OutputView.*;
import static javax.swing.GroupLayout.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JPanel implements IPanel, ActionListener {
    //<editor-fold desc="Variables Declarations">">
    private JPanel usersListPanel = new JPanel();
    private final JPanel headerPanel = new JPanel();
    private final JPanel profilePanel = new JPanel();
    private final JPanel chatPanel = new JPanel();

    private final JList<String> usersOnlineList = new JList<>();
    private final JList<String> usersOfflineList = new JList<>();

    private final JLabel appLogo = new JLabel(new ImageIcon("resources/Images/chat_bubble_100px.png"));
    private final JLabel appName = new JLabel("Chat Lab");
    private final JLabel currentUserLabel = new JLabel("Greeting !");
    private final JLabel userName = new JLabel("Oknaa");
    private final JLabel userIcon = new JLabel(new ImageIcon("resources/Images/male_user_100px.png"));

    private final JScrollPane usersOnlineListScroller = new JScrollPane();
    private final JScrollPane usersOfflineListScroller = new JScrollPane();
    private final JScrollPane chatScroller = new JScrollPane();
    private final JScrollPane inputScroller = new JScrollPane();

    public JTextArea chatArea = new JTextArea();
    private final JTextArea inputArea = new JTextArea();

    private final JButton logoutButton = new JButton(new ImageIcon("resources/Images/sign_out_70px.png"));
    private final JButton sendButton = new JButton(new ImageIcon("resources/Images/paper_plane_45px.png"));
    private final JButton attachButton = new JButton(new ImageIcon("resources/Images/attach_45px.png"));

    private final DefaultListModel<String> defaultListModel = new DefaultListModel<>();

    //</editor-fold>

    public MainPanel(ChatClient clientScript){
        this.client = clientScript;

        setPreferredSize(new Dimension(900, 500));
        SetupHeaderPanel();
        SetupProfilePanel();
        SetupUsersPanel();
        SetupChatPanel();
        SetupMainPanelLayout();
    }

    //<editor-fold desc="FrontEndStuff">
    private void SetupHeaderPanel() {
        headerPanel.setBackground(INDEPENDENCE);
        headerPanel.setPreferredSize(new Dimension(450, 500));
        appName.setFont(new Font("Source Code Pro", Font.PLAIN, 48));
        appName.setForeground(ISABELLINE);
        SetupHeaderPanelLayout();
    }
    private void SetupHeaderPanelLayout() {
        GroupLayout headerPanelLayout = new GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(headerPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(headerPanelLayout.createSequentialGroup()
                        .addComponent(appLogo)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(appName)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )
        );
        headerPanelLayout.setVerticalGroup(headerPanelLayout.createParallelGroup(Alignment.LEADING)
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

    private void SetupProfilePanel() {
        profilePanel.setBackground(INDEPENDENCE);
        profilePanel.setPreferredSize(new Dimension(450, 500));
        SetupUserName();
        SetupSubmitButton(logoutButton, this, true, "Logout");
        SetupProfilePanelLayout();
    }
    private void SetupUserName() {
        currentUserLabel.setFont(new Font("Source Code Pro", Font.PLAIN, 16)); // NOI18N
        currentUserLabel.setForeground(ISABELLINE);
        userName.setPreferredSize(new Dimension(200, 46));
        userName.setFont(new Font("Source Code Pro", Font.PLAIN, 36));
        userName.setForeground(ISABELLINE);
        userName.setText(GetCurrentUser());
    }
    private void SetupProfilePanelLayout() {
        GroupLayout profilePanelLayout = new GroupLayout(profilePanel);
        profilePanel.setLayout(profilePanelLayout);
        profilePanelLayout.setHorizontalGroup(
                profilePanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(profilePanelLayout.createSequentialGroup()
                                .addComponent(userIcon)
                                .addGap(22, 22, 22)
                                .addGroup(profilePanelLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(currentUserLabel)
                                        .addGroup(profilePanelLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(userName)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                                .addComponent(logoutButton, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        profilePanelLayout.setVerticalGroup(
                profilePanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, profilePanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(userIcon))
                        .addGroup(profilePanelLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(currentUserLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userName)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(Alignment.TRAILING, profilePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(logoutButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }

    private void SetupUsersPanel() {
        usersListPanel = new UserListPane(client);
        usersListPanel.setBackground(INDEPENDENCE);
        usersListPanel.setPreferredSize(new Dimension(450, 500));
        usersListPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
            }
        });
        //SetupUsersOnlineList();
        //SetupUsersOfflineList();
        //SetupChatScroller(usersOnlineListScroller, usersOnlineList);
        //SetupChatScroller(usersOfflineListScroller, usersOfflineList);
        //SetupUsersPanelLayout();
    }
    private void SetupUsersOnlineList() {
        usersOnlineList.setBackground(HELIOTROPE_GRAY);
        usersOnlineList.setForeground(ISABELLINE);
        usersOnlineList.setSelectionBackground(INDEPENDENCE);
        usersOnlineList.setSelectionForeground(ISABELLINE);

        usersOnlineList.setModel(new AbstractListModel<>() {
            String[] strings = { "User 1", "User 2", "User 3", "User 4", "User 5", "User 6" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }
    private void SetupUsersOfflineList() {
        usersOfflineList.setBackground(HELIOTROPE_GRAY);
        usersOfflineList.setForeground(ISABELLINE);
        usersOfflineList.setSelectionBackground(ISABELLINE);
        usersOfflineList.setSelectionForeground(HELIOTROPE_GRAY);
        usersOfflineList.setModel(new AbstractListModel<>() {
            String[] strings = { "User 7", "User 8", "User 9" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }
    private void SetupUsersPanelLayout() {
        GroupLayout usersListPanelLayout = new GroupLayout(usersListPanel);
        usersListPanel.setLayout(usersListPanelLayout);
        usersListPanelLayout.setHorizontalGroup( usersListPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(usersListPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(usersOnlineListScroller, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap()
                )
                .addGroup(usersListPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(usersOfflineListScroller, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap()
                )
        );
        usersListPanelLayout.setVerticalGroup(usersListPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(usersListPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(usersOnlineListScroller, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(usersOfflineListScroller, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap()
                )
        );
    }

    private void SetupChatPanel() {
        chatPanel.setBackground(INDEPENDENCE);
        chatPanel.setPreferredSize(new Dimension(450, 500));
        chatArea = GetChatField();
        SetupInputArea();
        SetupChatScroller(chatScroller, chatArea);
        SetupChatScroller(inputScroller, inputArea);
        SetupSubmitButton(attachButton, this, true, "Add file");
        SetupSubmitButton(sendButton, this, true, "Send");
        SetupChatPanelLayout();
    }
    private void SetupInputArea() {
        inputArea.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
        inputArea.setBorder(BorderFactory.createLineBorder(HELIOTROPE_GRAY, 3));
        inputArea.setBackground(INDEPENDENCE);
        inputArea.setForeground(ISABELLINE);
        inputArea.setColumns(20);
        inputArea.setRows(1);
        inputArea.setTabSize(1);
        inputArea.setText("Hahaa true !!");
        SwapSubmit_AndInsertBreak();
        inputScroller.setViewportView(inputArea);
    }
    private void SetupChatScroller(JScrollPane Scroller, JComponent viewPort) {
        Scroller.setBackground(INDEPENDENCE);
        Scroller.setBorder(null);
        Scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Scroller.setEnabled(false);
        Scroller.setFocusable(false);
        Scroller.setRequestFocusEnabled(false);
        Scroller.getVerticalScrollBar().setBackground(HELIOTROPE_GRAY);
        Scroller.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = ISABELLINE;
            }
        });
        Scroller.setViewportView(viewPort);
    }
    private void SwapSubmit_AndInsertBreak() {
        final String TEXT_SUBMIT = "text-submit";
        final String INSERT_BREAK = "insert-break";
        InputMap inputMap = inputArea.getInputMap();
        KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
        KeyStroke shiftEnter = KeyStroke.getKeyStroke("shift ENTER");
        inputMap.put(shiftEnter, INSERT_BREAK);
        inputMap.put(enter, TEXT_SUBMIT);
        ActionMap actionMap = inputArea.getActionMap();
        actionMap.put(TEXT_SUBMIT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnClick_SendMessage(userName, inputArea, chatArea);
            }
        });
    }
    private void SetupChatPanelLayout() {
        GroupLayout chatPanelLayout = new GroupLayout(chatPanel);
        chatPanel.setLayout(chatPanelLayout);
        chatPanelLayout.setHorizontalGroup(
                chatPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(chatPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(chatPanelLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(chatScroller, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                                        .addGroup(chatPanelLayout.createSequentialGroup()
                                                .addComponent(inputScroller)
                                                .addGap(18, 18, 18)
                                                .addComponent(attachButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        chatPanelLayout.setVerticalGroup(
                chatPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(chatPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(chatScroller, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(chatPanelLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(inputScroller, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                        .addComponent(sendButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(attachButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private void SetupMainPanelLayout() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(headerPanel, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                                        .addGap(0, 0, 0)
                                        .addComponent(profilePanel, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                                        .addGap(0, 0, 0)
                                        .addComponent(usersListPanel, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                )
        );
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(profilePanel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(usersListPanel, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                .addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                )
        );
    }


    @Override
    public JPanel GetPanel() { return this; }
    @Override
    public void Activate(){ this.setVisible(true);}
    @Override
    public void Deactivate(){ this.setVisible(false);}
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(logoutButton)) OnClick_Logout();
        else if (event.getSource().equals(sendButton)) OnClick_SendMessage(userName, inputArea, chatArea);
    }
    //</editor-fold>

    // Stuff that shouldn't be here D:
    private final ChatClient client;


}