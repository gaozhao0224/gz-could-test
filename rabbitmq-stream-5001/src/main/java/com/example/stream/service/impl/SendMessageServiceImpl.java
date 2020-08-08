package com.example.stream.service.impl;

import com.example.stream.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@EnableBinding(Source.class)
public class SendMessageServiceImpl implements SendMessageService {

    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        return null;
    }
}