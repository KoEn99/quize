package com.koen.quize.model;

import javax.persistence.*;

@Entity
@Table(name = "supply_quiz")
public class SupplyQuiz {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "answer_id", nullable = false)
    private Quiz quiz;
    @ManyToOne
    @JoinColumn(name = "authuser_id", nullable = false)
    private AuthUser authUser;
    @Column(name = "number_points", nullable = false, length = 100)
    private double numberPoints;
    @Column(name = "passed", nullable = false, length = 5)
    private boolean isPassed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public void setNumberPoints(Integer numberPoints) {
        this.numberPoints = numberPoints;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public double getNumberPoints() {
        return numberPoints;
    }

    public void setNumberPoints(double numberPoints) {
        this.numberPoints = numberPoints;
    }
}
