package com.dashboard.server.handler;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import com.dashboard.server.controller.KpiController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(KpiController.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, String>> handleArgumentException(Exception e) {
        LOGGER.error("예외발생!" + e.getMessage());

        HttpHeaders responHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생! " + e.getMessage());

        return new ResponseEntity<>(map, responHeaders, httpStatus);
    }
}
