package com.sd_utcn.secondHand.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sd_utcn.secondHand.model.Item;
import com.sd_utcn.secondHand.services.ItemService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@CrossOrigin("*")
@RequestMapping("/item")
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Item>> getItem() {
        List<Item> items = itemService.findItems();
        for(Item i : items){
            Link link = linkTo(methodOn(ItemController.class).getItem(i.getId_item())).withRel("userDetails");
            i.add(link);
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<UUID> insertUser(@Valid @RequestBody Item item){
        UUID id = itemService.insert(item);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Item> getItem(@PathVariable("id") UUID id){
        Item item = itemService.findItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UUID> updateItem(@PathVariable("id") UUID id, @Valid @RequestBody Item item) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.updateById(id, item));
    }
}
