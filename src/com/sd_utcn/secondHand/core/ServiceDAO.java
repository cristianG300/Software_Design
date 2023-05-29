package com.sd_utcn.secondHand.core;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.sd_utcn.secondHand.UI.exceptions.InputException;
import com.sd_utcn.secondHand.model.Item;
import com.sd_utcn.secondHand.model.ReportType;

import com.sd_utcn.secondHand.model.User;

public interface ServiceDAO {
    
    public boolean createAccount(final User user) throws NoSuchAlgorithmException, InvalidKeySpecException;
    
    public void postItem(final String username, final Item item) throws InputException;
    
    public void removeItem(final String username, final int id);
    
    public void createSingleReport(final String username, final ReportType reportType, final Item item);
    

}
