package site.shug.spring.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
@ComponentScan(basePackages = "site.shug.spring.webflux")
public class WebFluxConfig {
    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return route(GET("/hello"),
                request -> ServerResponse.ok().body(Mono.just("Hello, WebFlux!"), String.class));
    }
}
