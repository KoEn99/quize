package com.koen.quize.controller;

import com.koen.quize.Service.QuestionService;
import com.koen.quize.dto.AnswerServer;
import com.koen.quize.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AnswerServer> createQuestion(@RequestBody Question question){
        return questionService.save(question);
    }
}
