package com.koen.quize.repository;

import com.koen.quize.model.Answer;
import com.koen.quize.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question, Long> {
}
