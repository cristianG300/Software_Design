package com.sd_utcn.secondHand.builders;

import com.sd_utcn.secondHand.model.User;

public class UserBuilder {
    public UserBuilder(){}

    public static User toUser(User user) {
        if(user == null) return null;
        return new User(user.getFirstName(), user.getLastName(), user.getAddress(), user.getUsername(), user.getPassword().getPasswordStr());
    }
}
