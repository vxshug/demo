package site.shug.spring.mvc.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class InitAbstractAnnotationConfigDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // 配置类
        return new Class[] { WebConfig.class };
    }

    @Override
    protected void customizeRegistration(@NonNull ServletRegistration.Dynamic registration) {
        super.customizeRegistration(registration);
        System.out.println("customizeRegistration");
        registration.setMultipartConfig(new MultipartConfigElement("/tmp"));
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    @NonNull
    protected String[] getServletMappings() {
        // Servlet的映射
        return new String[] { "/*" };
    }
}
