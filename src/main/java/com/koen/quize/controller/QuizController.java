package com.koen.quize.controller;

import com.koen.quize.Service.QuizService;
import com.koen.quize.Service.ResultsUserService;
import com.koen.quize.dto.*;
import com.koen.quize.model.Answer;
import com.koen.quize.model.AuthUser;
import com.koen.quize.model.Quiz;
import com.koen.quize.model.ResultsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    ResultsUserService resultsUserService;
    @Autowired
    QuizService quizService;
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AnswerServer> createQuiz(@RequestBody Quiz quiz){
        return quizService.save(quiz);
    }
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Quiz getQuiz(@PathVariable long id){
        return quizService.getQuiz(id);
    }
    @RequestMapping(value = "/result", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AnswerServer> setResult(@RequestBody QuizResultDto quizResultDto){
        return resultsUserService.save(quizResultDto);
    }
   @RequestMapping(value = "/get/my", method = RequestMethod.GET)
    @ResponseBody
    public List<QuizProfile> getMyQuiz(){
        return quizService.getQuizMyAuth();
    }
    @RequestMapping(value = "/get/results", method = RequestMethod.GET)
    @ResponseBody
    public List<Results> getResults(){
        return resultsUserService.resultsGet();
    }
}
