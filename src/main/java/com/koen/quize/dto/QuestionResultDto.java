package com.koen.quize.dto;

import java.util.List;

public class QuestionResultDto {
    private String id_question;
    private List<String> answer_id;
    private String answer;

    public String getId_question() {
        return id_question;
    }

    public void setId_question(String id_question) {
        this.id_question = id_question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(List<String> answer_id) {
        this.answer_id = answer_id;
    }
}
