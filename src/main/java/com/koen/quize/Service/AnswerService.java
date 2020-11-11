package com.koen.quize.Service;

import com.koen.quize.dto.AnswerServer;
import com.koen.quize.model.Answer;
import com.koen.quize.model.Question;
import com.koen.quize.model.Quiz;
import com.koen.quize.repository.AnswerRepo;
import com.koen.quize.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerService implements ServiceOperations{
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    AnswerRepo answerRepo;
    @Override
    public ResponseEntity<AnswerServer> save(Object object) {
        try{
            Answer answer = (Answer) object;
            Optional<Question> question = questionRepo.findById(answer.getQuestion().getId());
            question.ifPresent(answer::setQuestion);
            answerRepo.save(answer);
            return new ResponseEntity<>(new AnswerServer("Ответ на впорос создан успешно!"), HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new AnswerServer("При создании ответа на вопрос произошла ошибка!"),HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<AnswerServer> remove(Object object) {
        return null;
    }
}
