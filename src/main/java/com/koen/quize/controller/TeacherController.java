package com.koen.quize.controller;

import com.koen.quize.Service.TeacherService;
import com.koen.quize.dto.AnswerServer;
import com.koen.quize.dto.GroupSubjectDto;
import com.koen.quize.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @RequestMapping(value = "/bind/group", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AnswerServer> bindGroupWithSubject(@RequestBody GroupSubjectDto groupSubjectDto){
        return teacherService.bindGroupWithSubject(groupSubjectDto);
    }
}
