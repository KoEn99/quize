package com.koen.quize.dto;

import com.koen.quize.model.Question;

import java.util.List;

public class QuizDto {
    private String title;
    private String description;
    private List<Question> question;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }
}
