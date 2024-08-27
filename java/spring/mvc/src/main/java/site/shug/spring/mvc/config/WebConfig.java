package site.shug.spring.mvc.config;

import jakarta.servlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import site.shug.spring.mvc.filter.MyResponseFilter;
import site.shug.spring.mvc.handler.ChatHandler;
import site.shug.spring.mvc.handler.ChatHandshakeInterceptor;

import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;

/**
 * `EnableWebMvc`开启Spring MVC的功能, 实际导入的是`DelegatingWebMvcConfiguration`这个`WebMvcConfigurationSupport`的默认子类,
 * 实现`WebMvcConfigurer`可以完成以下自定义功能, 如果不使用`EnableWebMvc`,
 * 可以继承`WebMvcConfigurationSupport`或`DelegatingWebMvcConfiguration`.
 */
@Configuration
@ComponentScan(basePackages = "site.shug.spring.mvc")
@EnableWebMvc
@EnableWebSocket
public class WebConfig implements WebMvcConfigurer, WebApplicationInitializer {
    /**
     * DelegatingFilterProxy可以将Spring容器的Bean在Filter中使用
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("OnStartup");
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        // 代理Bean的名称
        proxy.setTargetBeanName("myResponseFilter");
        FilterRegistration.Dynamic filter = servletContext.addFilter("MyFilter", proxy);
        filter.addMappingForUrlPatterns(null, false, "/*");
    }


    @Bean
    public WebMvcConfigurer createWebMvcConfigurer(HandlerInterceptor[] handlerInterceptors) {
        return new WebMvcConfigurer() {
            /**
             * 注册所有HandlerInterceptor
             */
            public void addInterceptors(InterceptorRegistry registry) {
                for (HandlerInterceptor interceptor : handlerInterceptors) {
                    registry.addInterceptor(interceptor);
                }
            }

            /**
             * 配置CORS
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .maxAge(3600);
            }
        };
    }

    @Primary
    @Bean
    LocaleResolver createLocaleResolver() {
        var clr = new CookieLocaleResolver();
        clr.setDefaultLocale(Locale.ENGLISH);
        clr.setDefaultTimeZone(TimeZone.getDefault());
        return clr;
    }
    @Bean("i18n")
    MessageSource createMessageSource() {
        var messageSource = new ResourceBundleMessageSource();
        // 指定文件是UTF-8编码:
        messageSource.setDefaultEncoding("UTF-8");
        // 指定主文件名:
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Bean
    WebSocketConfigurer createWebSocketConfigurer(
            @Autowired ChatHandler chatHandler,
            @Autowired ChatHandshakeInterceptor chatInterceptor)
    {
        return new WebSocketConfigurer() {
            public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
                // 把URL与指定的WebSocketHandler关联，可关联多个:
                registry.addHandler(chatHandler, "/chat").addInterceptors(chatInterceptor);
            }
        };
    }
}
