package site.shug.spring.aop.cal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    private final Object target;
    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * {@code Proxy.newProxyInstance}
     * 第一个参数是加载动态类的类加载器
     * 第二个参数是目标对象实现的所有接口的class对象
     * 第三个参数是目标对象方法调用方法的代理
     */
    public Object getProxy() {
        Object instance = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            /**
             *
             * @param proxy 调用该方法的代理实例
             *
             * @param method {@code Method}实例对应于在代理实例上调用的接口方法。
             * {@code Method}对象的声明类将是该方法被声明的接口，该接口可能是代理类继承该方法所通过的代理接口的超接口。
             *
             * @param args 方法的参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("[动态代理] 调用前" + method.getName());
                Object result = method.invoke(target, args);
                System.out.println("[动态代理] 调用后" + method.getName() + "结果: " + result);
                return result;
            }
        });
        return instance;
    }
}
