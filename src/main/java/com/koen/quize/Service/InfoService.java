package com.koen.quize.Service;

import com.koen.quize.dto.AnswerServer;
import com.koen.quize.model.Answer;
import com.koen.quize.model.Groups;
import com.koen.quize.model.Subject;
import com.koen.quize.repository.GroupsRepo;
import com.koen.quize.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class InfoService {
    @Autowired
    GroupsRepo groupsRepo;
    @Autowired
    SubjectRepo subjectRepo;

    public ResponseEntity getListGroups(){
        List<Groups> groupsList = groupsRepo.findAll();
        return ResponseEntity.ok(groupsList);
    }
    public ResponseEntity getListSubject(){
        List<Subject> subjectList = subjectRepo.findAll();
        return ResponseEntity.ok(subjectList);
    }
}
