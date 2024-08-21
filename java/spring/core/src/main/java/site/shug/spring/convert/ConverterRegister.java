package site.shug.spring.convert;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import java.util.Set;

@Configurable
public class ConverterRegister {
    /**
     * 将ConverterUser这个实现了{@code Converter<User, AdvancedUser>}的类注册到Spring容器中
     */
    @Bean
    public FormattingConversionServiceFactoryBean conversionService() {
        FormattingConversionServiceFactoryBean factoryBean = new FormattingConversionServiceFactoryBean();
        factoryBean.setConverters(Set.of(new ConverterUser()));
        return factoryBean;
    }
}
