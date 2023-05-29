package com.sd_utcn.secondHand.model;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;

@Table(name = "items")
@Entity
public class Item extends RepresentationModel<Item>{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id_item;

    @Column(name = "id", nullable = false)
    private int id = 1;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "category", nullable = false)
    private ClothesType category;

    @Column(name = "size", nullable = false)
    private Size size;
    
    public Item(final String title, ClothesType category, Size size, final float price) {
        this.id = incrementId();
        this.title = title;
        this.price = price;
        this.category = category;
        this.size = size;
    }

    public Item(int id, String title, ClothesType category, Size size, float price) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.size = size;
        this.price = price;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UUID getId_item() {
        return id_item;
    }

    public void setId_item(UUID id_item) {
        this.id_item = id_item;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public ClothesType getCategory() {
        return category;
    }

    public void setCategory(ClothesType category) {
        this.category = category;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o.getClass() != Item.class) {
            return false;
        }
        return this.toString().equals(((Item) o).toString());
    }
    
    @Override
    public String toString() {
        return title + ",\nCategory:" + category.toString() + ",\nSize: " + size.toString();
    }

    private int incrementId() {
       return id++;
    }

}
