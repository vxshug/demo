package site.shug.spring.method;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class ReplaceMethod implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("reimplement: " + args[0]);
        return "reimplement";
    }
}
