package com.koen.quize.Service;

import com.koen.quize.dto.AnswerServer;
import com.koen.quize.dto.QuizResultDto;
import com.koen.quize.dto.Results;
import com.koen.quize.model.*;
import com.koen.quize.repository.*;
import com.koen.quize.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResultsUserService implements ServiceOperations{
    @Autowired
    AnswerRepo answerRepo;
    @Autowired
    ResultsUserRepo resultsUserRepo;
    @Autowired
    AuthUserRepo authUserRepo;
    @Autowired
    QuizRepo quizRepo;
    int correctAnswer;
    ResultsUser resultsUser;
    QuizResultDto quizResultDto;
    Boolean isAllCorrectAnswer = false;
    @Override
    public ResponseEntity<AnswerServer> save(Object object) {

        Optional<Quiz> quiz = quizRepo.findById(Long.parseLong(quizResultDto.getId_quiz()));
        resultsUser.setQuiz(quiz.get());
        resultsUserRepo.save(resultsUser);
        return new ResponseEntity(new AnswerServer(Integer.toString(correctAnswer) +
                " правильных ответа из " +
                quizResultDto.getQuestionResultDtoList().size()), HttpStatus.OK);
    }
    /*public List<Results> resultsGet(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUser userDetails = (JwtUser) authentication.getPrincipal();
        AuthUser authUser = authUserRepo.findByEmail(userDetails.getEmail());
        List<Results> resultsList = new ArrayList<>();
        for (int i =0; i < authUser.getResultsUsers().size(); i++){
            Results results = new Results();
            results.setId(authUser.getResultsUsers().get(i).getQuiz().getId());
            results.setTitle(authUser.getResultsUsers().get(i).getQuiz().getTitle());
            results.setFio(authUser.getResultsUsers().get(i).getAuthUser().getFio());
            results.setEmail(authUser.getResultsUsers().get(i).getAuthUser().getEmail());
            results.setResults(authUser.getResultsUsers().get(i).getCorrectAnswer() + " из "
                    + authUser.getResultsUsers().get(i).getQuiz().getQuestionArrayList().size());
            resultsList.add(results);
        }
        return resultsList;
    }

     */
    public List<Results> resultsGet(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUser userDetails = (JwtUser) authentication.getPrincipal();
        AuthUser authUser = authUserRepo.findByEmail(userDetails.getEmail());
        List<Quiz> quizList = quizRepo.findAllByAuthUser_Id(authUser.getId());
       List<Results> resultsList = new ArrayList<>();
        for (int j = 0; j < quizList.size(); j++){
            int size =  quizList.get(j).getResultsUserArrayList().size();
            for (int i =0; i < size; i++){
                Results results = new Results();
                results.setId(quizList.get(j).getId());
                results.setTitle(quizList.get(j).getTitle());
                results.setFirst_name(quizList.get(j).getResultsUserArrayList().get(i).getAuthUser().getFist_name());
                results.setSecond_name(quizList.get(j).getResultsUserArrayList().get(i).getAuthUser().getSecond_name());
                results.setMiddle_name(quizList.get(j).getResultsUserArrayList().get(i).getAuthUser().getMiddle_name());
                results.setEmail(quizList.get(j).getResultsUserArrayList().get(i).getAuthUser().getEmail());
                results.setResults(quizList.get(j).getResultsUserArrayList().get(i).getCorrectAnswer() + " из "
                        + quizList.get(j).getQuestionArrayList().size());
                resultsList.add(results);
            }
        }

        return resultsList;
    }
    @Override
    public ResponseEntity<AnswerServer> remove(Object object) {
        return null;
    }
}
