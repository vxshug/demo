package site.shug.spring.convert;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

@Configurable
public class PropertyEditorRegister {
    /**
     * 将PropertyEditorUser这个实现了PropertyEditor的类注册到Spring容器中
     */
    @Bean
    public CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer configurer = new CustomEditorConfigurer();
        Map<Class<?>, Class<? extends PropertyEditor>> customEditors = new HashMap<>();
        customEditors.put(User.class, PropertyEditorUser.class);
        configurer.setCustomEditors(customEditors);
        return configurer;
    }
}
