package com.sd_utcn.secondHand.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sd_utcn.secondHand.SecondHandSystem;
import com.sd_utcn.secondHand.UI.exceptions.InputException;
import com.sd_utcn.secondHand.model.ClothesType;
import com.sd_utcn.secondHand.model.Item;
import com.sd_utcn.secondHand.model.Size;
import com.sd_utcn.secondHand.model.resources.Messages;

public class PostItem {
    JFrame frame = new JFrame("Post Item");
    GridBagConstraints c = new GridBagConstraints();
    
    JLabel postLabel;
    
    JLabel titleLabel;
    JTextField titleText;
    
    JLabel categoryLabel;
    JComboBox<String> categoryText;
    String[] categories = {"Shirt", "Pant", "Pullover"};
    
    JLabel sizeLabel;
    JComboBox<String> sizeText;
    String[] sizes = {"Small", "Medium", "Large"};
    
    JLabel priceLabel;
    JTextField priceText;

    JButton cancelButton;
    JButton postButton;
    
    public PostItem(final String username, final SecondHandSystem system) {
        initiateWindow(username, system);
    }
    
    private void initiateWindow(final String username, final SecondHandSystem system) {
        frame.getContentPane().setLayout(new GridBagLayout());
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        
        postLabel = new JLabel("Post Item");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        frame.getContentPane().add(postLabel, c);
        
        titleLabel = new JLabel("Title:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        frame.getContentPane().add(titleLabel, c);
        
        titleText = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        frame.getContentPane().add(titleText, c);
        
        categoryLabel = new JLabel("Category");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        frame.getContentPane().add(categoryLabel, c);
        
        categoryText = new JComboBox<String>(categories);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        frame.getContentPane().add(categoryText, c);
        
        sizeLabel = new JLabel("Size:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        frame.getContentPane().add(sizeLabel, c);
        
        sizeText = new JComboBox<String>(sizes);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        frame.getContentPane().add(sizeText, c);
        
        priceLabel = new JLabel("Price");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        frame.getContentPane().add(priceLabel, c);
        
        priceText = new JTextField(8);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        frame.getContentPane().add(priceText, c);
        
        cancelButton = new JButton("Cancel");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                openMainPage(username, system);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        frame.getContentPane().add(cancelButton, c);
        
        postButton = new JButton("Post");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        postButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClothesType category = ClothesType.valueOf((String) categoryText.getSelectedItem().
                            toString().toUpperCase());
                    Item item = new Item(titleText.getText(), category,
                            Size.valueOf((String) sizeText.getSelectedItem().toString().toUpperCase()),
                            Float.parseFloat(priceText.getText()));
                    system.postItem(username, item);
                    openSingleReport(item, "Posting");
                    JFrame successfulFrame = new JFrame("Success");
                    JOptionPane.showMessageDialog(successfulFrame, Messages.ITEM_POSTED);
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                } catch (NumberFormatException | InputException e1) {
                    e1.printStackTrace();
                }
            }
        });
        frame.getContentPane().add(postButton, c);
        
        frame.setVisible(true);
        
    }
    
    private ReportPopUp openSingleReport(final Item item, final String reportType) {
        return new ReportPopUp(item, reportType);
    }
    
    private MainPage openMainPage(final String username, final SecondHandSystem system) {
        return new MainPage(username, system);
    }

}
