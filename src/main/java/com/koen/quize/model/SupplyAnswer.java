package com.koen.quize.model;

import javax.persistence.*;

@Entity
@Table(name = "supply_answer")
public class SupplyAnswer {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
    @Column(name = "answer", nullable = false, length = 1000)
    private String answer;
    @Column(name = "number_points", nullable = false, length = 100)
    private double numberPoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public double getNumberPoints() {
        return numberPoints;
    }

    public void setNumberPoints(double numberPoints) {
        this.numberPoints = numberPoints;
    }
}
