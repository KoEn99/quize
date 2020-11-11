package com.koen.quize.Service;

import com.koen.quize.dto.AnswerServer;
import com.koen.quize.model.Groups;
import com.koen.quize.model.Subject;
import com.koen.quize.repository.GroupsRepo;
import com.koen.quize.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class AdminService {
    @Autowired
    SubjectRepo subjectRepo;
    @Autowired
    GroupsRepo groupsRepo;
    public ResponseEntity<String> createSubject(Subject subject){
        try {
            subjectRepo.save(subject);
        }
        catch (Exception ex){
            return new ResponseEntity<String>("При создании предмета произошла ошибка", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Предмет успешно создан", HttpStatus.CREATED);
    }
    public ResponseEntity<AnswerServer> createGroup(Groups groups){
        try {
            if (groupsRepo.findByName(groups.getName()) == null) groupsRepo.save(groups);
            else  return new ResponseEntity<AnswerServer>(new AnswerServer("Данный номер группы уже существует"), HttpStatus.CREATED);
        } catch (Exception ex){
            return new ResponseEntity<AnswerServer>(new AnswerServer("При создании группы произошла ошибка"), HttpStatus.CREATED);
        }
        return new ResponseEntity<AnswerServer>(new AnswerServer("Группа успешно создана"), HttpStatus.CREATED);
    }
}
