package ru.paymaster.emu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.paymaster.emu.event.InitReceivedEvent;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestController()
public class InitController {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping(value = "/payment/init", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> init(@RequestParam Map<String,String> allRequestParams) {
        log.info("/payment/init Thread = {} allRequestParams = {}", Thread.currentThread().getName(), allRequestParams);
        InitReceivedEvent initReceivedEvent = new InitReceivedEvent(new Object(), allRequestParams);
        applicationEventPublisher.publishEvent(initReceivedEvent);
        return new ResponseEntity<>("init " + LocalDateTime.now(), HttpStatus.OK);
    }
}
