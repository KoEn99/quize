package com.koen.quize.Service;

import com.koen.quize.dto.AnswerServer;
import com.koen.quize.dto.GroupSubjectDto;
import com.koen.quize.model.Groups;
import com.koen.quize.model.Subject;
import com.koen.quize.repository.GroupsRepo;
import com.koen.quize.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    SubjectRepo subjectRepo;
    @Autowired
    GroupsRepo groupsRepo;
    public ResponseEntity<AnswerServer> bindGroupWithSubject(GroupSubjectDto subjectDto){
        Optional<Subject> subject = subjectRepo.findById(subjectDto.getSubjectId());
        Optional<Groups> groups = groupsRepo.findById(subjectDto.getGroupsId());
        List<Subject> subjectList = groups.get().getSubject();
        subjectList.add(subject.get());
        groups.get().setSubject(subjectList);
        groups.ifPresent(value -> groupsRepo.save(value));
        return new ResponseEntity<AnswerServer>(new AnswerServer("Группа успешно добавлена в курс"), HttpStatus.OK);
    }
}
