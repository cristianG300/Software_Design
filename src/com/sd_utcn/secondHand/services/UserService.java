package com.sd_utcn.secondHand.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd_utcn.secondHand.UI.exceptions.ResourceNotFoundException;
import com.sd_utcn.secondHand.builders.UserBuilder;
import com.sd_utcn.secondHand.model.User;
import com.sd_utcn.secondHand.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsers(){
        List<User> userList = userRepository.findAll();
        return userList.stream().map(UserBuilder::toUser).collect(Collectors.toList());
    }

    public User findUserById(UUID id){
        Optional<User> prosumer = userRepository.findById(id);
        if(!prosumer.isPresent()){
            throw new ResourceNotFoundException(User.class.getSimpleName());
        }
        return UserBuilder.toUser(prosumer.get());
    }

    public UUID insert(User u){
        User user = UserBuilder.toUser(u);
        user = userRepository.save(user);
        return user.getId_user();
    }

    public UUID updateById(UUID id, User u) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User user = userRepository.findById(id).get();
        user.setFirstName(u.getFirstName());
        user.setLastName(u.getLastName());
        user.setAddress(u.getAddress());
        user.setUsername(u.getUsername());
        user.setPassword(u.getPassword().getPasswordStr());
        return userRepository.save(user).getId_user();
    }

}
