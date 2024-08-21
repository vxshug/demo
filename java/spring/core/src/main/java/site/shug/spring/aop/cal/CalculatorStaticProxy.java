package site.shug.spring.aop.cal;

/**
 * Calculator静态代理
 */
public class CalculatorStaticProxy implements Calculator {
    private final Calculator calculator;
    public CalculatorStaticProxy(Calculator calculator) {
        this.calculator = calculator;
    }
    @Override
    public int add(int a, int b) {
        System.out.println("[日志] " + "CalculatorStaticProxy.add 开始");
        int result = calculator.add(a, b);
        System.out.println("[日志] " + "CalculatorStaticProxy.add 结束, 结果是 " + result);
        return result;
    }

    @Override
    public int subtract(int a, int b) {
        System.out.println("[日志] " + "CalculatorStaticProxy.subtract 开始");
        int result = calculator.subtract(a, b);
        System.out.println("[日志] " + "CalculatorStaticProxy.subtract 结束, 结果是 " + result);
        return result;
    }

    @Override
    public int multiply(int a, int b) {
        System.out.println("[日志] " + "CalculatorStaticProxy.multiply 开始");
        int result = calculator.multiply(a, b);
        System.out.println("[日志] " + "CalculatorStaticProxy.multiply 结束, 结果是 " + result);
        return result;
    }

    @Override
    public int divide(int a, int b) {
        System.out.println("[日志] " + "CalculatorStaticProxy.divide 开始");
        int result = calculator.divide(a, b);
        System.out.println("[日志] " + "CalculatorStaticProxy.divide 结束, 结果是 " + result);
        return result;
    }
}
