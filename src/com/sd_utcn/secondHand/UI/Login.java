package com.sd_utcn.secondHand.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.swing.*;

import com.sd_utcn.secondHand.SecondHandSystem;
import com.sd_utcn.secondHand.model.Password;
import com.sd_utcn.secondHand.model.resources.Errors;
import com.sd_utcn.secondHand.model.resources.Messages;

public class Login {
    JFrame frame = new JFrame("Login");
    GridBagConstraints c = new GridBagConstraints();
    
    JLabel loginLabel;
    
    JLabel usernameLabel;
    JTextField usernameText;
    JLabel passwordLabel;
    JTextField passwordText;
    JButton loginButton;
    JButton cancelButton;
    JLabel noAccount;
    JButton createAccButton;
    JButton shutdown;
    
    public Login(final SecondHandSystem system) {
        initiateWindow(system);
    }
    
    private void initiateWindow(final SecondHandSystem system) {
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        
        loginLabel = new JLabel("Login");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        frame.getContentPane().add(loginLabel, c);
        
        usernameLabel = new JLabel("Username");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        frame.getContentPane().add(usernameLabel, c);
        
        usernameText = new JTextField(30);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        frame.getContentPane().add(usernameText, c);
        
        passwordLabel = new JLabel("Password");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        frame.getContentPane().add(passwordLabel, c);
        
        passwordText = new JTextField(30);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        frame.getContentPane().add(passwordText, c);
        
        loginButton = new JButton("Login");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(system.getUsers().containsKey(usernameText.getText()))) {
                    JFrame errorFrame = new JFrame("Error");
                    JOptionPane.showMessageDialog(errorFrame, "Error" + Errors.USER_NOT_EXISTENT);
                }
                try {
                    if (!(Password.validatePassword(system.getUsers().get(usernameText.getText()).getPassword().getPasswordStr(),
                           system.getUsers().get(usernameText.getText()).getPassword().getSecuredPasswordHash()))) {
                        JFrame errorFrame = new JFrame("Error");
                        JOptionPane.showMessageDialog(errorFrame, "Error" + Errors.WRONG_PASSWORD);
                    } else {
                        JFrame successfulFrame = new JFrame("Success");
                        JOptionPane.showMessageDialog(successfulFrame, Messages.LOGIN_SUCCESSFUL);
                        openMainPage(usernameText.getText(), system);
                    }
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
                    e1.printStackTrace();
                }
                
            }
        });
        frame.getContentPane().add(loginButton, c);
        
        cancelButton = new JButton("Cancel");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                usernameText.setText("");
                passwordText.setText("");
            }
        });
        frame.getContentPane().add(cancelButton, c);
        
        noAccount = new JLabel("No Account yet?");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        frame.getContentPane().add(noAccount, c);
        
        createAccButton = new JButton("Create a new account!");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        createAccButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                openRegistrationwindow(system);
                frame.setVisible(false);
            }
        });
        frame.getContentPane().add(createAccButton, c);
        
        shutdown = new JButton("Shutdown");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        shutdown.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                Runtime.getRuntime().exit(0);
            }
        });
        frame.getContentPane().add(shutdown, c);
        
        frame.setVisible(true);
    }
    
    private Registration openRegistrationwindow(final SecondHandSystem system) {
        return new Registration(system);
    }
    
    private MainPage openMainPage(final String username, final SecondHandSystem system) {
        return new MainPage(username, system);
    }
}
