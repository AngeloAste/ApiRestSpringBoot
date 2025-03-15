package com.challengercode.spring.apirest.controller;

import com.challengercode.spring.apirest.model.CallHistory;
import com.challengercode.spring.apirest.service.CallHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class CallHistoryController {

    @Autowired
    private CallHistoryService service;

    @GetMapping
    public List<CallHistory> getCallHistory() {
        return service.getCallHistory(); 
    }
}