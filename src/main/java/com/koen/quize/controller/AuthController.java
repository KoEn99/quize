package com.koen.quize.controller;

import com.koen.quize.Service.AuthService;
import com.koen.quize.dto.AnswerServer;
import com.koen.quize.dto.RegisterDto;
import com.koen.quize.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<AnswerServer> addAccount(@RequestBody AuthUser authUser){
        return authService.save(authUser);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AnswerServer> login(@RequestBody AuthUser authUser){
        return authService.checkLogin(authUser);
    }

}
