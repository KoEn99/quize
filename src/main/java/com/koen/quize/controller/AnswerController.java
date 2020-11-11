package com.koen.quize.controller;

import com.koen.quize.Service.AnswerService;
import com.koen.quize.dto.AnswerServer;
import com.koen.quize.model.Answer;
import com.koen.quize.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AnswerServer> createQuestion(@RequestBody Answer answer){
        return answerService.save(answer);
    }
}
