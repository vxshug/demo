package site.shug.spring.convert;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Configurable
public class FormatterRegister {
    /**
     * 将FormatterUser这个实现了Formatter的类注册到Spring容器中
     */
    @Bean
    public FormattingConversionServiceFactoryBean conversionService() {
        FormattingConversionServiceFactoryBean factoryBean = new FormattingConversionServiceFactoryBean();
        factoryBean.setFormatters(Set.of(new FormatterUser()));
        return factoryBean;
    }
}
