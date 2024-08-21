package site.shug.spring.aop.xml;

import org.springframework.stereotype.Component;

@Component
public class XmlCalculatorImpl implements XmlCalculator {

    @Override
    public int add(int a, int b) {
        System.out.println("CalculatorImpl.add: " + (a + b));
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        System.out.println("CalculatorImpl.subtract: " + (a - b));
        return a - b;
    }

    @Override
    public int multiply(int a, int b) throws Throwable {
        System.out.println("CalculatorImpl.multiply: " + (a * b));
        throw new Throwable("multiply Throwable");
    }

    @Override
    public int divide(int a, int b) {
        System.out.println("CalculatorImpl.divide: " + (a / b));
        return a / b;
    }
}
