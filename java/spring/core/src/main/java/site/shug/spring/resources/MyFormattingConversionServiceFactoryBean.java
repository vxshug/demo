package site.shug.spring.resources;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Set;

@Configurable
@Component
public class MyFormattingConversionServiceFactoryBean {
    @Bean
    public FormattingConversionServiceFactoryBean conversionService() {
        FormattingConversionServiceFactoryBean factoryBean = new FormattingConversionServiceFactoryBean();
        factoryBean.setConverters(Set.of(new ByteArrayResourceBean(), new InputStreamResourceBean()));
        return factoryBean;
    }
}
