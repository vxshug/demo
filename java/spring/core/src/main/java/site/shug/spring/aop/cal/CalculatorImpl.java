package site.shug.spring.aop.cal;

public class CalculatorImpl implements Calculator {

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
    public int multiply(int a, int b) {
        System.out.println("CalculatorImpl.multiply: " + (a * b));
        return  a * b;
    }

    @Override
    public int divide(int a, int b) {
        System.out.println("CalculatorImpl.divide: " + (a / b));
        return a / b;
    }
}
