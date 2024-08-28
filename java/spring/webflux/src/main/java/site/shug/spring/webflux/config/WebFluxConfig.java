package site.shug.spring.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import site.shug.spring.webflux.handler.MyServerAccessDeniedHandler;

import java.util.ArrayList;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
@EnableWebFluxSecurity
@ComponentScan(basePackages = "site.shug.spring.webflux")
public class WebFluxConfig {
    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return route(GET("/hello"),
                request -> ServerResponse.ok().body(Mono.just("Hello, WebFlux!"), String.class));
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .exceptionHandling(ex -> ex.accessDeniedHandler(new MyServerAccessDeniedHandler())) // 认证失败的处理器
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/login").permitAll()
                        .anyExchange().denyAll()
                );
        return http.build();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService userDetailsService) {
        return new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = new User("user", "{noop}user", new ArrayList<>());
        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
