package com.sd_utcn.secondHand.model;

public enum ClothesType {
    
    SHIRT("Shirt"),
    PANT("Pant"),
    PULLOVER("Pullover");
    
    private String shortName;
    
    private ClothesType(final String shortName) {
        this.shortName = shortName;
    }
    
    @Override
    public String toString() {
        return this.shortName;
    }

}
