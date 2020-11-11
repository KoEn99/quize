package com.koen.quize.Service;

import com.koen.quize.dto.RegisterDto;
import com.koen.quize.model.AuthUser;
import com.koen.quize.model.RolesUser;
import com.koen.quize.model.Subject;
import com.koen.quize.repository.AuthUserRepo;
import com.koen.quize.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {
    @Autowired
    SubjectRepo subjectRepo;
    @Autowired
    AuthUserRepo authUserRepo;
    public ResponseEntity<String> updateUserProfile(RegisterDto registerDto){
        try {
            AuthUser authUser = authUserRepo.findByEmail(registerDto.getEmail());
            authUser.setEmail(registerDto.getEmail());
            authUser.setFist_name(registerDto.getFirst_name());
            authUser.setSecond_name(registerDto.getSecond_name());
            authUser.setMiddle_name(registerDto.getMiddle_name());
          //  authUser.setGroup(registerDto.getGroup());
            List<Subject> subjectArrayList = new ArrayList<>();
            if (registerDto.getSubjectList() != null){
                for (int i = 0; i < registerDto.getSubjectList().size(); i++){
                    Optional<Subject> subject = subjectRepo.findById(registerDto.getSubjectList().get(i).getId());
                    subject.ifPresent(subjectArrayList::add);
                }
                authUser.setSubject(registerDto.getSubjectList());
            }
            authUser.setSubject(subjectArrayList);
            authUserRepo.save(authUser);
        }
        catch (Exception ex){
            return new ResponseEntity<String>("При обновлении профиля произошла ошибка",
                    HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Профиль успешно обновлен",
                HttpStatus.CREATED);
    }
}
