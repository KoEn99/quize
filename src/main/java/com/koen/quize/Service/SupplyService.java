package com.koen.quize.Service;

import com.koen.quize.dto.SupplyAnswerDto;
import com.koen.quize.model.*;
import com.koen.quize.repository.AuthUserRepo;
import com.koen.quize.repository.QuestionRepo;
import com.koen.quize.repository.SupplyAnswerRepo;
import com.koen.quize.repository.SupplyQuizRepo;
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
public class SupplyService {
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    SupplyQuizRepo supplyQuizRepo;
    @Autowired
    SupplyAnswerRepo supplyAnswerRepo;

    public ResponseEntity saveAnswer(List<SupplyAnswerDto> supplyAnswerDto){
        List<SupplyAnswer> supplyAnswer = new ArrayList<>();
        double totalPoint = 0;
        for (int i = 0; i < supplyAnswerDto.size(); i++){
            Optional<Question> question = questionRepo.findById(supplyAnswer.get(i).getQuestion().getId());
            supplyAnswer.get(i).setQuestion(question.get());
            for (int j = 0; j < question.get().getAnswerList().size(); j++){
                Answer answerCorrect = question.get().getAnswerList().get(j);
                if (answerCorrect.getAnswer().equals(supplyAnswerDto.get(i).getAnswerList().get(j))){
                   if (answerCorrect.isCorrect()) {
                       supplyAnswer.get(i).setNumberPoints(question.get().getPoints());
                       totalPoint+=question.get().getPoints();
                   }
                   else supplyAnswer.get(i).setNumberPoints(0.0);
                }
            }
        }
        supplyAnswerRepo.saveAll(supplyAnswer);
        saveSupplyQuiz(supplyAnswer, totalPoint);
        return new ResponseEntity(HttpStatus.OK);
    }
    @Autowired
    AuthUserRepo authUserRepo;
    private void saveSupplyQuiz(List<SupplyAnswer> supplyAnswer, double totalPoint){
        SupplyQuiz supplyQuiz = new SupplyQuiz();
        supplyQuiz.setNumberPoints(totalPoint);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUser userDetails = (JwtUser) authentication.getPrincipal();
        AuthUser authUser = authUserRepo.findByEmail(userDetails.getEmail());
        supplyQuiz.setAuthUser(authUser);
        supplyQuiz.setPassed(true);
        supplyQuiz.setQuiz(supplyAnswer.get(0).getQuestion().getQuiz());
        supplyQuizRepo.save(supplyQuiz);
    }
}
