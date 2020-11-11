package com.koen.quize.controller;

import com.koen.quize.Service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {
    @Autowired
    InfoService infoService;
    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public ResponseEntity getGroups(){
        return infoService.getListGroups();
    }
    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public ResponseEntity getSubject(){
        return infoService.getListSubject();
    }
}
