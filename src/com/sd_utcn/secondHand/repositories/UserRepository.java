package com.sd_utcn.secondHand.repositories;

import com.sd_utcn.secondHand.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    
}
