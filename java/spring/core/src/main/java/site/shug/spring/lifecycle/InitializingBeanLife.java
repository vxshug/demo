package site.shug.spring.lifecycle;

import org.springframework.beans.factory.InitializingBean;

public class InitializingBeanLife implements InitializingBean {
    public InitializingBeanLife() {
        System.out.println("InitializingBeanLife");
    }

    /**
     * 在Bean属性设置完成后调用
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBeanLife: afterPropertiesSet");
    }
}
