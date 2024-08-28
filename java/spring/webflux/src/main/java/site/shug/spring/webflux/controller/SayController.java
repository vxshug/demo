package site.shug.spring.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
@RequestMapping("/say")
public class SayController {
    @GetMapping("/hello")
    Mono<String> sayHello() {
        return Mono.just("Hello World");
    }

    @GetMapping("/ex")
    Mono<String> sayException() {
        throw new RuntimeException("sayException");
    }

    @GetMapping(value = "/stream", produces = TEXT_EVENT_STREAM_VALUE)
    Flux<String> stream() {
        return Flux.just("1", "2", "3", "4", "5", "6", "7", "8", "9")
                .delayElements(Duration.ofSeconds(1));
    }
}
