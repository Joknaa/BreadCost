package com.muc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SignUpWindow implements ActionListener {

    private final ChatClient client;
    JTextField t1, t2, t3;
    JButton next;
    JFrame jf = new JFrame("SIGNING UP");
    String login;
    String pwd;
    private JLabel et2, et1, et3;

    public SignUpWindow() {
        this.client = new ChatClient("localhost", 8818);
        client.connect();

        t1 = new JTextField();
        t1.setBounds(150, 80, 250, 25);
        t2 = new JPasswordField();
        t2.setBounds(150, 140, 250, 25);
        t3 = new JPasswordField();
        t3.setBounds(150, 200, 250, 25);

        jf.add(t1);
        jf.add(t3);
        et1 = new JLabel("LOGIN            : ");
        et1.setBounds(10, 80, 100, 20);
        jf.add(et1);
        jf.add(t2);
        et2 = new JLabel("PASSWORD : ");
        et2.setBounds(10, 140, 100, 20);

        et3 = new JLabel("CONFIRM PWD : ");
        et3.setBounds(10, 200, 100, 20);

        jf.add(et2);
        jf.add(et3);

        next = new JButton("> SignUp");
        next.setBounds(170, 240, 100, 50);
        next.addActionListener(this);
        jf.add(next);
        jf.setSize(450, 350);
        jf.setLocation(600, 300);
        jf.setLayout(null);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new SignUpWindow();
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (t1.getText().trim().isEmpty()
                    || t2.getText().trim().isEmpty()
                    || t3.getText().trim().isEmpty()) return;
            if (e.getSource() == next) {
                login = t1.getText();
                String password = t2.getText();
                String confirmPwd = t3.getText();
                if (password.equals(confirmPwd) && client.signUp(login, password, confirmPwd) == 1) {

                    JOptionPane.showMessageDialog(et1, "SignedUp !!", "SignedUp !!", 1, null);
                    client.signUp(login, password, confirmPwd);
                    jf.setVisible(false);
                    new LoginWindow();
                } else if (client.signUp(login, password, confirmPwd) == 2) {
                    JOptionPane.showMessageDialog(et1, "Existing ACCOUNT !!", "Not SignedUp !!", 0, null);
                } else if (!password.equals(confirmPwd) || client.signUp(login, password, confirmPwd) == 3) {
                    JOptionPane.showMessageDialog(et1, "Password is not fit !!", "Not SignedUp !!", 0, null);
                } else {
                    JOptionPane.showMessageDialog(et1, "ERROR!!", "Not SignedUp !!", 0, null);
                }
            }
            System.out.println("Done");
        } catch (Exception s) {
            s.printStackTrace();
        }


    }

}