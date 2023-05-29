package com.sd_utcn.secondHand.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.sd_utcn.secondHand.SecondHandSystem;
import com.sd_utcn.secondHand.UI.exceptions.InputException;
import com.sd_utcn.secondHand.core.utility.MultiMap;
import com.sd_utcn.secondHand.model.Item;
import com.sd_utcn.secondHand.model.resources.Messages;

/**
 * shows the main page for a certain user. He receives a list of all the items he can buy
 * (his own items are not available)
 */
public class MainPage {
    
    private final static String BLANK_FIELD = "";
    
    private Object[][] itemsData;
    
    //comparator to sort the items by their ID
    Comparator<Object[]> idComparator = new Comparator<Object[]>() {
        @Override
        public int compare(Object[] o1, Object[] o2) {
            Integer id1 = (Integer) o1[0];
            Integer id2 = (Integer) o2[0];
            return id1.compareTo(id2);
        }
    };
    
    JFrame frame = new JFrame("Main Page");
    GridBagConstraints c = new GridBagConstraints();
    
    JLabel mainPageLabel;
    JLabel noAvItems;
    JButton logoutButton;
    JButton deleteAccButton;
    JButton changePwButton;
    
    JTable itemsTable;
    //last column used for the buy-button
    private final String[] columnNames = {"ID", "Title", "Category", "Size", "Price", BLANK_FIELD};
    
    JScrollPane sp;
    JButton buyButton;
    JButton postButton;
    JButton myItems;
    JButton showReportButton;
    
    JButton shutdown;
    
    public MainPage(final String username, final SecondHandSystem system) {
        initiateWindow(username, system);
    }
    
    private void initiateWindow(final String username, final SecondHandSystem system) {
        frame.getContentPane().setLayout(new GridBagLayout());
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        
        if (availableItems(username, system).isEmpty()) {
            noAvItems = new JLabel("No items to buy yet!\nWait for users to post items.");
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth = 3;
            frame.getContentPane().add(noAvItems, c);
        } else {
            itemsData = generateTableContent(username, system);
            
            //Table for all the items
            itemsTable = new JTable(itemsData, columnNames);
            itemsTable.setFillsViewportHeight(true);
            
            sp = new JScrollPane(itemsTable);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth = 3;
            frame.getContentPane().add(sp, c);
            
        }
        
        mainPageLabel = new JLabel("Main Page");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        frame.getContentPane().add(mainPageLabel, c);
        
        //Logout
        logoutButton = new JButton("Logout");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        logoutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame successfulFrame = new JFrame("Success");
                JOptionPane.showMessageDialog(successfulFrame, Messages.LOGOUT_SUCCESSFUL);
                openLogin(system);
                frame.setVisible(false);
            }
        });
        frame.getContentPane().add(logoutButton, c);
        
        //delete Account
        deleteAccButton = new JButton("Delete Account");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        deleteAccButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                system.deleteAccount(username);
                JFrame successfulFrame = new JFrame("Success");
                JOptionPane.showMessageDialog(successfulFrame, Messages.ACC_DELETED);
                openRegistration(system);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        frame.getContentPane().add(deleteAccButton, c);
        
        //change PW
        changePwButton = new JButton("Change Password");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        changePwButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                openChangePw(username, system);
            }
        });
        frame.getContentPane().add(changePwButton, c);
        
        //Post item
        postButton = new JButton("Post");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        postButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                openPostItem(username, system);
            }
        });
        frame.getContentPane().add(postButton, c);
        
        //Show Report
        showReportButton = new JButton("Show Report list");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        showReportButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFrame reportListFrame = new JFrame("Report List");
                    JOptionPane.showMessageDialog(reportListFrame, system.getReportList(username));
                } catch (HeadlessException | InputException e1) {
                    e1.printStackTrace();
                }
            }
        });
        frame.getContentPane().add(showReportButton, c);
        
        shutdown = new JButton("Shutdown");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
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
    
    
    //converts the multimap to a double array for the table elements
    private Object[][] generateTableContent(final String username, final SecondHandSystem system) {
        MultiMap<String, Item> availableItems = availableItems(username, system);
        
        Object[][] itemsArray = new Object[availableItems.size()][];
        int index = 0;
        
        for (String key : availableItems.keySet()) {
            Collection<Item> itemCollection = system.getItems().get(key);
            for (Item i : itemCollection) {
                //Buy item
                buyButton = new JButton("Buy");
                buyButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        system.removeItem(key, i.getId());
                        openSingleReport(i, "Buying");
                        JFrame successfulFrame = new JFrame("Success");
                        JOptionPane.showMessageDialog(successfulFrame, Messages.ITEM_BOUGHT);
                    }
                });
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 1;
                c.gridy = 2;
                c.gridwidth = 1;
                frame.getContentPane().add(buyButton);
                
                itemsArray[index] = createTableEntry(i, buyButton);
                index++;
            }
        }
        //sort the array and finally return it
        Arrays.sort(itemsArray, idComparator);
        return itemsArray;
    }
    
    private Object[]createTableEntry(final Item item, final JButton buyButton) {
        //the last cell of a table entry is a button to buy the item
        Object[] tableEntry = {item.getId(), item.getTitle(), item.getCategory(), item.getSize(), item.getPrice()};
        return tableEntry;
    }
    
    private MultiMap<String, Item> availableItems(final String username, final SecondHandSystem system) {
        MultiMap<String, Item> availableItems = system.getItems().copy();
        //remove the items of the logged in user
        availableItems.remove(username);
        return availableItems;
    }
    
    private Login openLogin(final SecondHandSystem system) {
        return new Login(system);
    }
    
    private Registration openRegistration(final SecondHandSystem system) {
        return new Registration(system);
    }
    
    private ChangePassword openChangePw(final String username, final SecondHandSystem system) {
        return new ChangePassword(username, system);
    }
    
    private PostItem openPostItem(final String username, final SecondHandSystem system) {
        return new PostItem(username, system);
    }
    
    private ReportPopUp openSingleReport(final Item item, final String reportType) {
        return new ReportPopUp(item, reportType);
    }
    
}
