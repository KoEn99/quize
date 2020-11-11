package com.koen.quize.controller;

import com.koen.quize.Service.SupplyService;
import com.koen.quize.dto.AnswerServer;
import com.koen.quize.dto.SupplyAnswerDto;
import com.koen.quize.model.SupplyAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supply")
public class SupplyController {
    @Autowired
    SupplyService supplyService;
    @RequestMapping(value = "/results", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createSupply(@RequestBody List<SupplyAnswerDto> supplyAnswer){
        return supplyService.saveAnswer(supplyAnswer);
    }
}
