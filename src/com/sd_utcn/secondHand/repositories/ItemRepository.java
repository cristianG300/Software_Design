package com.sd_utcn.secondHand.repositories;

import com.sd_utcn.secondHand.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID>  {
    
}
