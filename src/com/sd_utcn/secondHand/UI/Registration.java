package com.sd_utcn.secondHand.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sd_utcn.secondHand.SecondHandSystem;
import com.sd_utcn.secondHand.model.Address;
import com.sd_utcn.secondHand.model.resources.Errors;

import com.sd_utcn.secondHand.model.User;

public class Registration {
    
    JFrame frame = new JFrame("Registration");
    GridBagConstraints c = new GridBagConstraints();
    
    JLabel registerLabel;
    
    JLabel firstNameLabel;
    JTextField firstNameText;
    
    JLabel lastNameLabel;
    JTextField lastNameText;
    
    JLabel streetLabel;
    JTextField streetText;
    
    JLabel streetNoLabel;
    JTextField streetNoText;
    
    JLabel zipCodeLabel;
    JTextField zipCodeText;
    
    JLabel cityLabel;
    JTextField cityText;
    
    JLabel usernameLabel;
    JTextField usernameText;
    
    JLabel passwordLabel;
    JTextField passwordText;
    
    JButton registerButton;
    JButton shutdown;
    
    public Registration(final SecondHandSystem system) {
        initiateWindow(system);
    }
    
    private void initiateWindow(final SecondHandSystem system) {
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.setSize(800,400);
        
        registerLabel = new JLabel("Registration");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        frame.getContentPane().add(registerLabel, c);
        
        firstNameLabel = new JLabel("First name");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        frame.getContentPane().add(firstNameLabel, c);
        
        firstNameText = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        frame.getContentPane().add(firstNameText, c);
        
        lastNameLabel = new JLabel("Last name");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        frame.getContentPane().add(lastNameLabel, c);
        
        lastNameText = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        frame.getContentPane().add(lastNameText, c);
        
        streetLabel = new JLabel("Street");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        frame.getContentPane().add(streetLabel, c);
        
        streetText = new JTextField(30);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        frame.getContentPane().add(streetText, c);
        
        streetNoLabel = new JLabel("No.");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        frame.getContentPane().add(streetNoLabel, c);
        
        streetNoText = new JTextField(5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 1;
        frame.getContentPane().add(streetNoText, c);
        
        zipCodeLabel = new JLabel("ZIP Code");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        frame.getContentPane().add(zipCodeLabel, c);
        
        zipCodeText = new JTextField(6);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        frame.getContentPane().add(zipCodeText, c);
        
        cityLabel = new JLabel("City");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 1;
        frame.getContentPane().add(cityLabel, c);
        
        cityText = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 1;
        frame.getContentPane().add(cityText, c);
        
        usernameLabel = new JLabel("Username");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        frame.getContentPane().add(usernameLabel, c);
        
        usernameText = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        frame.getContentPane().add(usernameText, c);
        
        passwordLabel = new JLabel("Password");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        frame.getContentPane().add(passwordLabel, c);
        
        passwordText = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 4;
        c.gridwidth = 1;
        frame.getContentPane().add(passwordText, c);
        
        registerButton = new JButton("Create Account");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(streetNoText.getText());
                    System.out.println("Input is an integer: " + value);
                } catch (NumberFormatException e1) {
                    System.out.println("Input is not an integer: " + streetNoText.getText());
                }
                try {
                    int value = Integer.parseInt(zipCodeText.getText());
                    System.out.println("Input is an integer: " + value);
                } catch (NumberFormatException e2) {
                    System.out.println("Input is not an integer: " + zipCodeText.getText());
                }
                //check if user already exists
                if (system.getUsers().get(usernameText.getText()) != null) {
                    JFrame errorFrame = new JFrame("Error");
                    JOptionPane.showMessageDialog(errorFrame, "Error" + Errors.USERNAME_ALREADY_EXISTS);
                    clearTextFields();
                } else {
                    Address addr = new Address(streetText.getText(), streetNoText.getText(),
                        Integer.parseInt(zipCodeText.getText()), cityText.getText());
                    User newUser = new User(firstNameText.getText(), lastNameText.getText(), addr,
                            usernameText.getText(), passwordText.getText());
                    try {
                        newUser.getPassword().generateStorngPasswordHash(newUser.getPassword().getPasswordStr());
                    } catch (NoSuchAlgorithmException e3) {
                        e3.printStackTrace();
                    } catch (InvalidKeySpecException e3) {
                        e3.printStackTrace();
                    }
                    system.createAccount(newUser);
                    openMainPage(newUser.getUsername(), system);
                    frame.setVisible(false);
                }
            }
        });
        frame.getContentPane().add(registerButton, c);
        
        shutdown = new JButton("Shutdown");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 3;
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
    
    private void clearTextFields() {
        streetText.setText("");
        streetNoText.setText("");
        zipCodeText.setText("");
        cityText.setText("");
        firstNameText.setText("");
        lastNameText.setText("");
        usernameText.setText("");
        passwordText.setText("");
    }
    
    private MainPage openMainPage(final String username, final SecondHandSystem system) {
        return new MainPage(username, system);
    }

}
