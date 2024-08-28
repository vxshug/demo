package site.shug.spring.mvc.config;

import jakarta.servlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;
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
import site.shug.spring.mvc.filter.FixedUserFilter;
import site.shug.spring.mvc.handler.ChatHandler;
import site.shug.spring.mvc.handler.ChatHandshakeInterceptor;
import site.shug.spring.mvc.handler.CustomAccessDeniedHandler;

import java.util.ArrayList;
import java.util.List;
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
@EnableWebSecurity
@EnableMethodSecurity
public class WebConfig implements WebMvcConfigurer, WebApplicationInitializer {
    /**
     * DelegatingFilterProxy可以将Spring容器的Bean在Filter中使用
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("OnStartup");
        DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy("springSecurityFilterChain");
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        // 代理Bean的名称
        proxy.setTargetBeanName("myResponseFilter");
        FilterRegistration.Dynamic filter = servletContext.addFilter("MyFilter", proxy);
        FilterRegistration.Dynamic springSecurityFilter = servletContext.addFilter("springSecurityFilter", springSecurityFilterChain);
        filter.addMappingForUrlPatterns(null, false, "/*");
        springSecurityFilter.addMappingForUrlPatterns(null, false, "/*");
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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserDetailsService userDetailsService,
                                           PasswordEncoder passwordEncoder) throws Exception {
        FixedUserFilter fixedUserFilter = new FixedUserFilter();
        http
                .exceptionHandling(ex -> ex.accessDeniedHandler(new CustomAccessDeniedHandler())) // 认证失败的处理器
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/blog/**")).permitAll() // 允许/blog/*的路径
                        .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/private/*")).hasAuthority("PRIVATE")
                        .anyRequest().authenticated() // 其他路径全部需要授权
                )
//                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable) // 禁用csrf
                .addFilterBefore(fixedUserFilter, AnonymousAuthenticationFilter.class)
                .rememberMe(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public UserDetailsService createUserDetailsService() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));

        UserDetails user = new User("user", "{noop}user", authorities);
        return new InMemoryUserDetailsManager(user);
    }
}
