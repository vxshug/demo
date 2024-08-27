package site.shug.spring.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/say")
public class SayController {
    @GetMapping("/hello")
    Mono<String> sayHello() {
        return Mono.just("Hello World");
    }
}
