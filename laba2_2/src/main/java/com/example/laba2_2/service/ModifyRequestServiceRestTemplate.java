package com.example.laba2_2.service;

import com.example.laba2_2.model.Request;
import com.example.laba2_2.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Slf4j
@Service
@Qualifier("ModifyRequestServiceRestTemplate")
public class ModifyRequestServiceRestTemplate implements ModifyRequestService {

    @Override
    public void modify(Request request) {
        request.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));

        log.info("Отправка запроса в Сервис 2: {}", request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Request> entity = new HttpEntity<>(request, headers);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8084/feedback";

        try {
            String response = restTemplate.postForObject(url, entity, String.class);
            log.info("Ответ от Сервиса 2: {}", response);
        } catch (Exception e) {
            log.error("Ошибка при отправке в Сервис 2: {}", e.getMessage());
        }
    }
}