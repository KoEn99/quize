package com.koen.quize.repository;

import com.koen.quize.model.RolesUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<RolesUser, Long> {
    RolesUser findByName(String name);
}