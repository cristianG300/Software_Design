package com.sd_utcn.secondHand.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import jakarta.persistence.*;

import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;
import org.hibernate.annotations.GenericGenerator;

@Table(name = "users")
@Entity
public class User extends RepresentationModel<User> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id_user;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "address", nullable = false)
    private Address address;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private Password password;
    
    public User(String firstName, String lastName, Address address, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.username = username;
        this.password = new Password(password);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UUID getId_user() {
        return id_user;
    }

    public void setId_user(UUID id_user) {
        this.id_user = id_user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Password getPassword() {
        return this.password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
            this.password = new Password(password);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o.getClass() != User.class) {
            return false;
        }
        return this.toString().equals(((User) o).toString());
    }
    
    @Override
    public String toString() {
        return this.username;
    }

}
