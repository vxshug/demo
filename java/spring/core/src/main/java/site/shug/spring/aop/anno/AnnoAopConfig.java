package site.shug.spring.aop.anno;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy // 开启AspectJ的动态代理
@ComponentScan(basePackages = "site.shug.spring.aop.anno")
public class AnnoAopConfig { }
