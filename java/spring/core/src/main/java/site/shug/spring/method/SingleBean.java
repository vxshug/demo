package site.shug.spring.method;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SingleBean implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public SingleBean() {
        System.out.println("SingleBean");
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void getNoSingleBean() {
        applicationContext.getBean(NoSingleBean.class);
        System.out.println("getNoSingleBean");
    }
}
