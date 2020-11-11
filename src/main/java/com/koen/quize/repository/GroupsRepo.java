package com.koen.quize.repository;

import com.koen.quize.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepo extends JpaRepository<Groups, Long> {
    Groups findByName(String name);
}
