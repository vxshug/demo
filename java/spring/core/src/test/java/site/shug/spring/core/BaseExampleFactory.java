package site.shug.spring.core;

public class BaseExampleFactory {
    public BeanExample createBeanExample() {
        System.out.println("createBeanExample");
        return new BeanExample();
    }
}
