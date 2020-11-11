package com.koen.quize.dto;

import com.koen.quize.model.Answer;

import java.util.List;

public class QuestionDto {
    private String title;
    private double points;
    private List<Answer> answer;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}
