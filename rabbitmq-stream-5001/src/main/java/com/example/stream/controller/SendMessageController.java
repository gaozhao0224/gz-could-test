package com.example.stream.controller;

import com.example.stream.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    @Autowired
    private SendMessageService sendMessageService;


}