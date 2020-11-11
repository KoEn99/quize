package com.koen.quize.Service;

import com.koen.quize.dto.AnswerServer;
import com.koen.quize.dto.QuizDto;
import com.koen.quize.dto.QuizProfile;
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
public class QuizService implements ServiceOperations{
    @Autowired
    QuizRepo quizRepo;
    @Autowired
    AuthUserRepo authUserRepo;
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    AnswerRepo answerRepo;
    @Autowired
    SubjectRepo subjectRepo;
    Quiz quiz;
    @Override
    public ResponseEntity<AnswerServer> save(Object object) {
            quiz = (Quiz) object;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            JwtUser userDetails = (JwtUser) authentication.getPrincipal();
            AuthUser authUser = authUserRepo.findByEmail(userDetails.getEmail());
            quiz.setAuthUser(authUser);
           // addQuestionAndAnswer();
            Optional<Subject> subject = subjectRepo.findById(quiz.getSubject().getId());
            quiz.setSubject(subject.get());
            quizRepo.save(quiz);
        return new ResponseEntity(new AnswerServer("Тест успешно создан"), HttpStatus.OK);
    }
    private void addQuestionAndAnswer(){
        List<Answer> answerList = new ArrayList<>();
        for (int i = 0; i < quiz.getQuestionArrayList().size(); i++){
            for (int j = 0; j < quiz.getQuestionArrayList().get(i).getAnswerList().size(); j++){
                Answer answer = quiz.getQuestionArrayList().get(i).getAnswerList().get(j);
                answer.setQuestion(quiz.getQuestionArrayList().get(i));
                answerList.add(answer);
            }
          //  quiz.getQuestionArrayList().get(i).setAnswerList(answerList);
            quiz.getQuestionArrayList().get(i).setQuiz(quiz);
        }
    }
    public Quiz getQuiz(Long id){
        Optional<Quiz> quizOptional = quizRepo.findById(id);
        Quiz quiz = quizOptional.get();
        return quiz;
    }
    public List<QuizProfile> getQuizMyAuth(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUser userDetails = (JwtUser) authentication.getPrincipal();
        AuthUser authUser = authUserRepo.findByEmail(userDetails.getEmail());
        List<Quiz> quizMyAuthList = quizRepo.findAllByAuthUser_Id(authUser.getId());
        List<QuizProfile> quizProfilesList = new ArrayList<>();
        for (int i = 0; i < quizMyAuthList.size(); i++){
            QuizProfile quizProfile = new QuizProfile();
            quizProfile.setId(quizMyAuthList.get(i).getId());
            quizProfile.setDescription(quizMyAuthList.get(i).getDescription());
            quizProfile.setTitle(quizMyAuthList.get(i).getTitle());
            quizProfilesList.add(quizProfile);
        }
        return quizProfilesList;
    }
    @Override
    public ResponseEntity<AnswerServer> remove(Object object) {
        return null;
    }
}
