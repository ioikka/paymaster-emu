package ru.paymaster.emu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.paymaster.emu.event.InitReceivedEvent;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component
public class InitReceivedEventListener {

    private final RestTemplate restTemplate;

    @Autowired
    public InitReceivedEventListener(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    @EventListener
    public void handleCustomSpringEvent(InitReceivedEvent cse) {
        log.info("thre = {} {}", Thread.currentThread().getName(), cse);


        Map<String, String> map = cse.getMessage();
        String lmiInvoiceConfirmationUrl = map.get("LMI_INVOICE_CONFIRMATION_URL");
        String lmiPaymentNotificationUrl = map.get("LMI_PAYMENT_NOTIFICATION_URL");

        LinkedMultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<>();

        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            linkedMultiValueMap.add(stringStringEntry.getKey(), stringStringEntry.getValue());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(linkedMultiValueMap, headers);
        lmiInvoiceConfirmationUrl = getUrlDecoded(lmiInvoiceConfirmationUrl);
        lmiPaymentNotificationUrl = getUrlDecoded(lmiPaymentNotificationUrl);



        ResponseEntity<String> responseEntity = restTemplate.exchange(lmiInvoiceConfirmationUrl,
                HttpMethod.POST,
                entity,
                String.class);

        log.info("responseEntity = {}, Thread = {}", responseEntity, Thread.currentThread().getName());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            log.error("", e);
        }
        responseEntity = restTemplate.exchange(lmiPaymentNotificationUrl,
                HttpMethod.POST,
                entity,
                String.class);
        log.info("responseEntity = {}, Thread = {}", responseEntity, Thread.currentThread().getName());
    }

    private String getUrlDecoded(String strToDecode) {
        try {
            strToDecode = URLDecoder.decode(strToDecode, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            log.error("", e);
        }
        return strToDecode;
    }
}