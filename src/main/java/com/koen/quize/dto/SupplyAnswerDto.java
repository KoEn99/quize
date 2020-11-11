package com.koen.quize.dto;

import com.koen.quize.model.Question;

import java.util.List;

public class SupplyAnswerDto {
    Question question;
    List<String> answerList;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<String> answerList) {
        this.answerList = answerList;
    }
}
