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
import com.sd_utcn.secondHand.model.resources.Messages;

public class ChangePassword {
    JFrame frame = new JFrame("Change Password");
    GridBagConstraints c = new GridBagConstraints();

    JLabel changePwLabel;
    
    JLabel oldPwLabel;
    JTextField oldPwText;
    
    JLabel newPwLabel;
    JTextField newPwText;
    
    JButton cancel;
    JButton confirm;
    
    public ChangePassword(final String username, final SecondHandSystem system) {
        initiateWindow(username, system);
    }
    
    private void initiateWindow(final String username, final SecondHandSystem system) {
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.setSize(200,200);
        
        changePwLabel = new JLabel("Change Password");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        frame.getContentPane().add(changePwLabel, c);
        
        oldPwLabel = new JLabel("Old Password");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        frame.getContentPane().add(oldPwLabel, c);
        
        oldPwText = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        frame.getContentPane().add(oldPwText, c);
        
        newPwLabel = new JLabel("New Password");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        frame.getContentPane().add(newPwLabel, c);
        
        newPwText = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        frame.getContentPane().add(newPwText, c);
        
        cancel = new JButton("Cancel");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        frame.getContentPane().add(cancel, c);
        
        confirm = new JButton("Confirm");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        confirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (system.changePassword(username, oldPwText.getText(), newPwText.getText())) {
                        JFrame successfulFrame = new JFrame("Success");
                        JOptionPane.showMessageDialog(successfulFrame, Messages.PW_CHANGE_SUCCESS);
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    }
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
                    e1.printStackTrace();
                }
                
            }
        });
        frame.getContentPane().add(confirm, c);
        
        frame.setVisible(true);        
    }
}
