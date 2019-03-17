package ru.paymaster.emu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
public class PaymentNotificationController {

    @PostMapping(value = "/payment/notification", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> notification(@RequestParam Map<String,String> allRequestParams) {
        log.info("notification {} Thread = {}", allRequestParams, Thread.currentThread().getName());
        return new ResponseEntity<>("YES" + LocalDateTime.now(), HttpStatus.OK);
    }
}
