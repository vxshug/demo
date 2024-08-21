package site.shug.spring.aop.anno;

public interface AnnoCalculator {
    int add(int a, int b);
    int subtract(int a, int b);
    int multiply(int a, int b) throws Throwable;
    int divide(int a, int b);
}
