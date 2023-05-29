package com.sd_utcn.secondHand.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd_utcn.secondHand.UI.exceptions.ResourceNotFoundException;
import com.sd_utcn.secondHand.builders.ItemBuilder;
import com.sd_utcn.secondHand.model.Item;
import com.sd_utcn.secondHand.repositories.ItemRepository;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findItems() {
        List<Item> itemList = itemRepository.findAll();
        return itemList.stream().map(ItemBuilder::toItem).collect(Collectors.toList());
        
    }

    public Item findItemById(UUID id){
        Optional<Item> prosumer = itemRepository.findById(id);
        if(!prosumer.isPresent()){
            throw new ResourceNotFoundException(Item.class.getSimpleName());
        }
        return ItemBuilder.toItem(prosumer.get());
    }

    public UUID insert(Item i){
        Item item = ItemBuilder.toItem(i);
        item = itemRepository.save(item);
        return item.getId_item();
    }

    public UUID updateById(UUID id, Item i) {
        Item item = itemRepository.findById(id).get();
        item.setId(i.getId());
        item.setTitle(i.getTitle());
        item.setCategory(i.getCategory());
        item.setSize(i.getSize());
        item.setPrice(i.getPrice());
        return itemRepository.save(item).getId_item();
    }
}
