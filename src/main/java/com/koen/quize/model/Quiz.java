package com.koen.quize.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, length = 1000)
    private String title;
    @Column(name = "description", nullable = false, length = 1000)
    private String description;
    @Column(name = "publish", nullable = false, length = 5)
    private boolean isPublish;
    @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Question> questionArrayList = new ArrayList<>();
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ResultsUser> resultsUserArrayList = new ArrayList<>();
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SupplyQuiz> supplyQuiz = new ArrayList<>();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "authuser_email", nullable = false)
    private AuthUser authUser;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Question> getQuestionArrayList() {
        return questionArrayList;
    }

    public void setQuestionArrayList(List<Question> questionArrayList) {
        this.questionArrayList = questionArrayList;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public List<ResultsUser> getResultsUserArrayList() {
        return resultsUserArrayList;
    }

    public void setResultsUserArrayList(List<ResultsUser> resultsUserArrayList) {
        this.resultsUserArrayList = resultsUserArrayList;
    }

    public boolean isPublish() {
        return isPublish;
    }

    public void setPublish(boolean publish) {
        isPublish = publish;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    @JsonIgnore
    public List<SupplyQuiz> getSupplyQuiz() {
        return supplyQuiz;
    }

    public void setSupplyQuiz(List<SupplyQuiz> supplyQuiz) {
        this.supplyQuiz = supplyQuiz;
    }
}
