package com.sd_utcn.secondHand.builders;

import com.sd_utcn.secondHand.model.Item;

public class ItemBuilder {
    public ItemBuilder(){}

    public static Item toItem(Item item) {
        if (item == null) return null;
        return new Item(item.getId(), item.getTitle(), item.getCategory(), item.getSize(), item.getPrice());
    }
}
