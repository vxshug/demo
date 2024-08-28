package site.shug.spring.mvc;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        // 手动创建Connector, 如果要tomcat创建默认Connector, 需要调用tomcat.getConnector()才会创建
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);
        Context context = tomcat.addWebapp("", new File("mvc/src/main/webapp").getAbsolutePath());
        // 创建 DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet();
        // 配置Spring容器
        servlet.setContextClass(org.springframework.web.context.support.AnnotationConfigWebApplicationContext.class);
        servlet.setContextConfigLocation("site.shug.spring.mvc.config.WebConfig");
        Tomcat.addServlet(context, "MVC", servlet);
        context.addServletMappingDecoded("/*", "MVC");
        tomcat.start();
        tomcat.getServer().await();
    }
}