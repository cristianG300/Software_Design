package com.sd_utcn.secondHand;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import com.sd_utcn.secondHand.UI.exceptions.InputException;
import com.sd_utcn.secondHand.core.ServiceDAO;
import com.sd_utcn.secondHand.core.UserDAO;
import com.sd_utcn.secondHand.core.utility.MultiMap;
import com.sd_utcn.secondHand.model.Item;
import com.sd_utcn.secondHand.model.Report;
import com.sd_utcn.secondHand.model.ReportType;
import com.sd_utcn.secondHand.model.resources.Errors;

import com.sd_utcn.secondHand.model.User;

public class SecondHandSystem implements ServiceDAO, UserDAO {
    
    private MultiMap<String, Item> items = new MultiMap<String, Item>();
    private HashMap<String, User> users = new HashMap<String, User>();
    private MultiMap<String, Report> reports = new MultiMap<String, Report>();
    
    public SecondHandSystem() {
        
    }
    
    //creates an account for a new user
    public boolean createAccount(final User user) {
        if (users.containsKey(user.getUsername())) {
            return false;
        }
        users.put(user.getUsername(), user);
        items.init(user.getUsername());
        reports.init(user.getUsername());
        return true;
    }

    @Override
    public boolean changePassword(final String username, final String oldPassword, final String newPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (users.get(username).getPassword().getPasswordStr().equals(oldPassword)) {
            users.get(username).setPassword(newPassword);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAccount(final String username) {
        users.remove(username);
        items.get(username).clear();
        items.remove(username);
        reports.get(username).clear();
        reports.remove(username);
    }

    @Override
    public User getUser(final String username) {
        return users.get(username);
    }
    
    //puts a new item to the system if not already existent
    public void postItem(String username, Item item)
            throws InputException {
        if (items.get(username).isEmpty() || items.get(username) == null) {
            items.put(username, item);
            return;
        }
        for (Item i : items.get(username)) {
            if (i.equals(item)) {
                throw new InputException(Errors.ITEM_ALREADY_EXISTS);
            }
        }
        items.get(username).add(item);
    }
    
    //by removing an item the belonging report will also get removed
    public void removeItem(final String username, final int id) {
        for (Item i : items.get(username)) {
            if (i.getId() == id) {
                items.get(username).remove(i);
                break;
            }
        }
        //remove belonging report
        for (Report r : reports.get(username)) {
            if (r.getItemId() == id) {
                reports.get(username).remove(r);
                break;
            }
        }
    }
    
    //creates a single report for a user based on the report type
    public void createSingleReport(final String username, final ReportType reportType, final Item item) {
        final StringBuilder result = new StringBuilder();
        
        switch(reportType) {
        case SINGLEPOST:
            result.append("Item " + item.getTitle() + " with ID " + item.getId() + " successfully posted!");
        case SINGLEPURCHASE:
            result.append("Item " + item.getTitle() + " with ID " + item.getId() + " successfully purchased!")
                    .append("\nThis item will be sent to the address: " + users.get(username).getAddress().toString());
        default:
            break;
        }
        reports.get(username).add(new Report(result.toString(), reportType, item.getId()));
    }
    
    //get a list of all reports of a certain report type from a specific user
    public String getReportList(final String username) throws InputException {
        StringBuilder result = new StringBuilder();
        for (Report r : reports.get(username)) {
            result.append(r.toString() + "\n");
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    public MultiMap<String, Item> getItems() {
        return items;
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public MultiMap<String, Report> getReports() {
        return reports;
    }

}
