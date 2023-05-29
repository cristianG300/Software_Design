package com.sd_utcn.secondHand.model;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Table(name = "addresses")
@Entity
public class Address {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id_address;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "zipCode", nullable = false)
    private int zipCode;

    @Column(name = "city", nullable = false)
    private String city;
    
    public Address(String street, String number, int zipCode, String city) {
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UUID getId_address() {
        return id_address;
    }

    public void setId_address(UUID id_address) {
        this.id_address = id_address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPostalCode() {
        return zipCode;
    }

    public void setPostalCode(int postalCode) {
        this.zipCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o.getClass() != Address.class) {
            return false;
        }
        return this.toString().equals(((Address) o).toString());
    }
    
    @Override
    public String toString() {
        return this.street + " " + this.number + "\n" + Integer.toString(zipCode) + " " + this.city;
    }

}
