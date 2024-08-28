package site.shug.spring.webflux.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ExControllerAdvice {
    @ExceptionHandler(value = Exception.class)
    public Mono<String> exceptionHandler(Exception e) {
        return Mono.just(e.getMessage());
    }
}
