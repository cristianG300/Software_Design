package com.sd_utcn.secondHand.model;

public enum ReportType {
    
    SINGLEPOST("Post"),
    SINGLEPURCHASE("Purchase"),
    POSTS(""),
    PURCHASES("");
    
private final String shortName;
    
    private ReportType(final String shortName) {
        this.shortName = shortName;
    }
    
    @Override
    public String toString() {
        return this.shortName;
    }

}
