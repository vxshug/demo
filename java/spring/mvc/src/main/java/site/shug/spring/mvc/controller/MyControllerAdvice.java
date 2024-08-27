package site.shug.spring.mvc.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(value = Exception.class)
    Map<String, String> handler(Exception e) {
        Map<String, String> map = new HashMap<>();
        map.put("msg", e.getMessage());
        return map;
    }
}
