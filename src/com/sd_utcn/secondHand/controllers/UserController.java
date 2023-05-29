package com.sd_utcn.secondHand.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import javax.validation.Valid;
import java.util.UUID;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sd_utcn.secondHand.model.User;
import com.sd_utcn.secondHand.services.UserService;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<User>> getUser(){
        List<User> users =userService.findUsers();
        for(User u : users){
            Link link = linkTo(methodOn(UserController.class).getUser(u.getId_user())).withRel("userDetails");
            u.add(link);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<UUID> insertUser(@Valid @RequestBody User user){
        UUID id = userService.insert(user);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") UUID id){
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UUID> updateUser(@PathVariable("id") UUID id, @Valid @RequestBody User user)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateById(id, user));
    }
}
