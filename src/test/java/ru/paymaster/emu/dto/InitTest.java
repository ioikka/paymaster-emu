package ru.paymaster.emu.dto;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
public class InitTest {

    @Test
    public void getLmiPaymentNotificationUrl() {
        try {
            String encode = URLEncoder.encode("http://localhost:8080/payment/confirmation", StandardCharsets.UTF_8.name());
            log.info(encode);
            encode = URLEncoder.encode("http://localhost:8080/payment/notification", StandardCharsets.UTF_8.name());
            log.info(encode);
        } catch (UnsupportedEncodingException e) {
            log.error("", e);
        }
    }

    @Test
    public void get(){
        String str = "http://localhost:8080/payment/init?" +
                "PAYMENT_UUID=" + UUID.randomUUID().toString() +
                "&LMI_INVOICE_CONFIRMATION_URL=http%3A%2F%2Flocalhost%3A8080%2Fpayment%2Fconfirmation" +
                "&LMI_PAYMENT_NOTIFICATION_URL=http%3A%2F%2Flocalhost%3A8080%2Fpayment%2Fnotification";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        RestTemplate restTemplate = new RestTemplate();


        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(new LinkedMultiValueMap<>(), headers);

        ResponseEntity<String> forEntity = restTemplate.exchange(
                str,
                HttpMethod.GET,
                entity,
                String.class);
        log.info("{}", forEntity);
    }
}