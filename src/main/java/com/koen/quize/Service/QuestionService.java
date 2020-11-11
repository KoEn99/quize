package com.koen.quize.Service;

import com.koen.quize.dto.AnswerServer;
import com.koen.quize.model.AuthUser;
import com.koen.quize.model.Question;
import com.koen.quize.model.Quiz;
import com.koen.quize.repository.AuthUserRepo;
import com.koen.quize.repository.QuestionRepo;
import com.koen.quize.repository.QuizRepo;
import com.koen.quize.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService implements ServiceOperations {
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    AuthUserRepo authUserRepo;
    @Autowired
    QuizRepo quizRepo;
    @Override
    public ResponseEntity<AnswerServer> save(Object object) {
        try{
            Question question = (Question) object;
            Optional<Quiz> quiz = quizRepo.findById(question.getQuiz().getId());
            quiz.ifPresent(question::setQuiz);
            questionRepo.save(question);
            return new ResponseEntity<>(new AnswerServer("Вопрос создан успешно!"), HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new AnswerServer("При создании вопроса произошла ошибка!"),HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<AnswerServer> remove(Object object) {
        return null;
    }
}
