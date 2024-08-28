package site.shug.spring.webflux;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;
import site.shug.spring.webflux.config.WebFluxConfig;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(WebFluxConfig.class);
        HttpHandler httpHandler = WebHttpHandlerBuilder.applicationContext(context).build();
        HttpServer.create()
                .port(8080)
                .handle(new ReactorHttpHandlerAdapter(httpHandler))
                .bindNow()
                .onDispose()
                .block();
    }
}