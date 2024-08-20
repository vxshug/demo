package site.shug.spring.method;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class SingleBeanLook {
    private ApplicationContext applicationContext;
    public abstract NoSingleBeanLook getNoSingleBean();
}
