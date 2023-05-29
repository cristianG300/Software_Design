package com.sd_utcn.secondHand.core;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.sd_utcn.secondHand.model.User;

public interface UserDAO {

    //changing the password of the user, verifying if the user knows his old password
    public boolean changePassword(final String username, final String oldPassword, final String newPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException;

    public void deleteAccount(final String username);
    
    public User getUser(final String username);
}
