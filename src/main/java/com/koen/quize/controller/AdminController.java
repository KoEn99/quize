package com.koen.quize.controller;

import com.koen.quize.Service.AdminService;
import com.koen.quize.Service.AuthService;
import com.koen.quize.Service.UserProfileService;
import com.koen.quize.dto.AnswerServer;
import com.koen.quize.dto.RegisterDto;
import com.koen.quize.model.Groups;
import com.koen.quize.model.Subject;
import com.koen.quize.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserProfileService userProfileService;
    @RequestMapping(value = "/subject/create", method = RequestMethod.POST)
    public ResponseEntity<String> createSubject(@RequestBody Subject subject){
        return adminService.createSubject(subject);
    }
    @RequestMapping(value = "/group/create", method = RequestMethod.POST)
    public ResponseEntity<AnswerServer> createGroup(@RequestBody Groups groups){
        return adminService.createGroup(groups);
    }
    @RequestMapping(value = "/profile/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> updateUserProfile(@RequestBody RegisterDto registerDto){
        return userProfileService.updateUserProfile(registerDto);
    }
}
