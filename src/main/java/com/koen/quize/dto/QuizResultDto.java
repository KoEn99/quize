package com.koen.quize.dto;

import java.util.List;

public class QuizResultDto {
    private String id_quiz;
    List<QuestionResultDto> questionResultDtoList;

    public String getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(String id_quiz) {
        this.id_quiz = id_quiz;
    }

    public List<QuestionResultDto> getQuestionResultDtoList() {
        return questionResultDtoList;
    }

    public void setQuestionResultDtoList(List<QuestionResultDto> questionResultDtoList) {
        this.questionResultDtoList = questionResultDtoList;
    }
}
