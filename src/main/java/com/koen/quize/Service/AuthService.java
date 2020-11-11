package com.koen.quize.Service;

import com.koen.quize.dto.AnswerServer;
import com.koen.quize.dto.RegisterDto;
import com.koen.quize.model.AuthUser;
import com.koen.quize.model.Groups;
import com.koen.quize.model.RolesUser;
import com.koen.quize.model.Subject;
import com.koen.quize.repository.AuthUserRepo;
import com.koen.quize.repository.GroupsRepo;
import com.koen.quize.repository.RolesRepo;
import com.koen.quize.repository.SubjectRepo;
import com.koen.quize.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService implements ServiceOperations{
    @Autowired
    SubjectRepo subjectRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RolesRepo rolesRepo;
    @Autowired
    AuthUserRepo authUserRepo;
    @Autowired
    GroupsRepo groupsRepo;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    public AuthUser authUser;
    @Override
    public ResponseEntity<AnswerServer> save(Object object) {
        authUser = (AuthUser) object;
        if (authUserRepo.findByEmail(authUser.getEmail()) == null) {
            authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
            List<RolesUser> rolesUserList = new ArrayList<>();
            for (int i = 0; i<authUser.getRoles().size(); i++){
                RolesUser rolesUser = rolesRepo.findByName(authUser.getRoles().get(i).getName());
                rolesUserList.add(rolesUser);
            }
            if (authUser.getSubject() != null) authUser.setSubject(getSubjectList());
            if (authUser.getGroups() != null) authUser.setGroups(addGroupsStudent());
            authUser.setRoles(rolesUserList);
            authUserRepo.save(authUser);
            return new ResponseEntity<>(new AnswerServer("Регистрация прошла успешно"), HttpStatus.CREATED);
        } else return new ResponseEntity<>(new AnswerServer("Аккаунт с таким email уже существует"), HttpStatus.CONFLICT);
    }
    public Groups addGroupsStudent(){
        return groupsRepo.findById(authUser.getGroups().getId()).get();
    }
    public List<Subject> getSubjectList(){
        List<Subject> subjectArrayList = new ArrayList<>();
        for (int i = 0; i < authUser.getSubject().size(); i++){
            Optional<Subject> subject = subjectRepo.findById(authUser.getSubject().get(i).getId());
            subject.ifPresent(subjectArrayList::add);
        }
        return subjectArrayList;
    }
    public ResponseEntity<AnswerServer> checkLogin(AuthUser authUser) {
        try {
            String email = authUser.getEmail();
            AuthUser authPerson = authUserRepo.findByEmail(email);
            if (authPerson != null) {
                String password = authUser.getPassword();
                if (!passwordEncoder.matches(password, authPerson.getPassword())) {
                    throw new BadCredentialsException("Неверный пароль");
                }
            } else throw new BadCredentialsException("Данный email не зарегестрирован");
            String token = jwtTokenProvider.createToken(authUser.getEmail(), authPerson.getRoles());
            return new ResponseEntity<>(new AnswerServer(token),HttpStatus.CREATED);
        }
        catch (AuthenticationException e){//throw new BadCredentialsException(e.getMessage());
            return new ResponseEntity<>(new AnswerServer(e.getMessage()), HttpStatus.CONFLICT);
        }
    }
    /*   private Map<Object, Object> getResponse(String email){
         //  String token = jwtTokenProvider.createToken(email);
           //long number = 259200000 * 10;
           //String token_refresh = jwtTokenProvider.createToken(email, number);
         //  Map<Object, Object> response = new HashMap<>();
       //    response.put("token", token);
           //response.put("token_refresh", token_refresh);
     //      return response;
       }

     */
    @Override
    public ResponseEntity<AnswerServer> remove(Object object) {
        return null;
    }
}
