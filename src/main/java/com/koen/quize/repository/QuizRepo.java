package com.koen.quize.repository;

import com.koen.quize.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QuizRepo extends JpaRepository<Quiz, Long> {
    List<Quiz> findAllByAuthUser_Id(Long id);
}
