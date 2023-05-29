package com.sd_utcn.secondHand.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sd_utcn.secondHand.model.Item;

public class ReportPopUp {
    
    JFrame frame = new JFrame("Report");
    GridBagConstraints c = new GridBagConstraints();
    
    JLabel reportTitleLabel;
    JButton close;
    
    public ReportPopUp(final Item item, final String reportType) {
        initiateWindow(item, reportType);
    }
    
    private void initiateWindow(final Item item, final String reportType) {
        frame.getContentPane().setLayout(new GridBagLayout());
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        
        reportTitleLabel = new JLabel(reportType + " report of item " + "\"" + item.toString() + "\"");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        frame.getContentPane().add(reportTitleLabel, c);
        
        frame.setVisible(true);
    }

}
