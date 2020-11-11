package com.koen.quize.dto;

public class AnswerServer {
    private String answer;

    public AnswerServer(String answer){
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
