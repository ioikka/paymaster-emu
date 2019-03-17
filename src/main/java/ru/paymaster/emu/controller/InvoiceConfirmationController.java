package ru.paymaster.emu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestController
public class InvoiceConfirmationController {


    @PostMapping(value = "/payment/confirmation", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> confirmation(@RequestParam Map<String,String> allRequestParams) {
        log.info("confirmation {} Thread = {}", allRequestParams, Thread.currentThread().getName());

        return new ResponseEntity<>("YES" + LocalDateTime.now(), HttpStatus.OK);
    }
}
