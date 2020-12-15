package com.koen.quize.dto;

import com.koen.quize.model.Question;

import java.util.List;

public class SupplyAnswerDto {
    Question question;
    String answer;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
