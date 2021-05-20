package com.muc.NewUI;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.muc.NewUI.OutputView.DisplayConfirmation;
import static com.muc.NewUI.OutputView.OnClick_Logout;

public class AppFrame extends JFrame{
    JPanel currentPanel;

    public void SetCurrentPanel(IPanel iPanel){
        currentPanel = iPanel.GetPanel();
        currentPanel.setVisible(true);
        add(currentPanel);
        SetupFrame();
    }

    public JPanel GetCurrentPanel(){ return this.currentPanel; }

    private void SetupFrame() {
        SetupFrameLayout();
        pack();
        setLocationRelativeTo(null);
    }
    private void SetupFrameLayout() {
        GroupLayout mainLayout = new GroupLayout(getContentPane());

        getContentPane().setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
                mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(currentPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainLayout.setVerticalGroup(
                mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(currentPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }
    public void SetupOnTimeConfiguration() {
        setResizable(false);
        setUndecorated(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                int PromptResult = DisplayConfirmation("Confirmation", "Are you sure you want to exit?");
                if (PromptResult == JOptionPane.YES_OPTION) {
                    OnClick_Logout();
                    System.exit(0);
                }
            }
        });
    }
}
