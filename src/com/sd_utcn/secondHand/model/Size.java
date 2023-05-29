package com.sd_utcn.secondHand.model;

public enum Size {
    
    SMALL("S"),
    MEDIUM("M"),
    LARGE("L");
    
private final String shortName;
    
    private Size(final String shortName) {
        this.shortName = shortName;
    }
    
    @Override
    public String toString() {
        return this.shortName;
    }

}
