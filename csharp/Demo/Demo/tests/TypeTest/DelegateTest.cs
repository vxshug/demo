namespace Demo.tests.TypeTest;

/**
 * 在C#中，委托（delegate）是一种引用类型，允许封装对具有特定参数列表和返回类型的方法的引用。
 * 可以将委托看作是一个函数指针，但它是类型安全的，并且能够引用静态方法和实例方法。
 */

public class Calculator
{
    public int Add(int a, int b)
    {
        return a + b;
    }
    public int Subtract(int a, int b) 
    { 
        return a - b; 
    }

    public void NoValue(int a, int b)
    {
        
    }

    public bool Neg(int a)
    {
        return a < 0;
    }
}

// 可以引用任何返回 int 类型并且接受两个 int 参数的方法。
public delegate int MathOperation(int x, int y);

[TestClass]
public class DelegateTest
{
    [TestMethod]
    public void TestDegate()
    {
        var calculator = new Calculator();
        MathOperation mathOperation = calculator.Add;
        Assert.AreEqual(10, mathOperation(5, 5));
        mathOperation = calculator.Subtract;
        Assert.AreEqual(5, mathOperation(10, 5));
    }
    
    /**
     * 委托可以是多播的，引用多个方法。当调用多播委托时，所有方法将按照添加的顺序被依次调用.
     * 多播委托的返回值将是最后一个被调用的方法的返回值。
     */
    [TestMethod]
    public void TestMutilDegate()
    {
        var calculator = new Calculator();
        MathOperation mathOperation = calculator.Add;
        mathOperation += calculator.Subtract;
        Assert.AreEqual(5, mathOperation(10, 5));
    }

    [TestMethod]
    public void TestFunc()
    {
        var calculator = new Calculator();
        // Func最后一个泛型参数是返回类型，其余是参数类型。
        Func<int, int, int> func = calculator.Add;
        Assert.AreEqual(10, func(5, 5));
    }
    [TestMethod]
    public void TestAction()
    {
        var calculator = new Calculator();
        // Action: 不返回值的委托，最多可以有 16 个参数。
        Action<int, int> action = calculator.NoValue;
    }
    [TestMethod]
    public void TestPredicate()
    {
        var calculator = new Calculator();
        // Predicate: 返回 bool ，接受一个参数。
        Predicate<int> predicate = calculator.Neg;
    }
}