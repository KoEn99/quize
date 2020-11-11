package com.koen.quize.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "auth_user")
public class AuthUser {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "fist_name", nullable = false, length = 100)
    private String fist_name;
    @Column(name = "second_name", nullable = false, length = 100)
    private String second_name;
    @Column(name = "middle_name", nullable = false, length = 100)
    private String middle_name;
    @ManyToOne
    @JoinColumn(name = "groups_id", nullable = true)
    private Groups groups;
    @OneToMany(mappedBy = "authUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Quiz> quizList = new ArrayList<>();
    @OneToMany(mappedBy = "authUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ResultsUser> resultsUsers = new ArrayList<>();
    @OneToMany(mappedBy = "authUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ResultsUser> resultsUsers1 = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<RolesUser> roles;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_subject",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id", referencedColumnName = "id")})
    private List<Subject> subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    public List<ResultsUser> getResultsUsers() {
        return resultsUsers;
    }

    public void setResultsUsers(List<ResultsUser> resultsUsers) {
        this.resultsUsers = resultsUsers;
    }

    public List<RolesUser> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesUser> roles) {
        this.roles = roles;
    }

    public List<ResultsUser> getResultsUsers1() {
        return resultsUsers1;
    }

    public void setResultsUsers1(List<ResultsUser> resultsUsers1) {
        this.resultsUsers1 = resultsUsers1;
    }

    public String getFist_name() {
        return fist_name;
    }

    public void setFist_name(String fist_name) {
        this.fist_name = fist_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
}
