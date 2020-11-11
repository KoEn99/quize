package com.koen.quize.Service;

import com.koen.quize.dto.AnswerServer;
import org.springframework.http.ResponseEntity;

public interface ServiceOperations {
    ResponseEntity<AnswerServer> save(Object object);
    ResponseEntity<AnswerServer> remove(Object object);
}
