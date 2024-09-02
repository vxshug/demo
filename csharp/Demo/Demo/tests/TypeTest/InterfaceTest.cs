namespace Demo.tests.TypeTest;

/**
 * 接口定义协定。
 * 实现该协定的任何 class、record 或 struct 必须提供接口中定义的成员的实现。
 * 接口可为成员定义默认实现。
 * 它还可以定义 static 成员，以便提供常见功能的单个实现。
 * 
 * 接口声明可以包含以下成员的声明:
 * - 方法
 * - 属性
 * - 索引器
 * - 事件
 */

interface ITest
{
    //方法
    void Test();
    // 属性
    public int F1 { get; set; }
    // 索引器
    public int this[int i]
    {
        get => i;
        set => F1 = value;
    }
    // 事件
    public event Action<string> OnMessageReceived;
}

interface IStaticFunc
{
    // 默认实现
    public static int ReturnOne()
    {
        return 1;
    }
}

interface IStaticAbstract
{
    public static abstract int AReturnOne();
}

interface IStaticVirtual<T> where T : IStaticVirtual<T>
{
    public static virtual int SReturnOne()
    {
        return 1;
    }

    public static int SReturnTwo()
    {
        return T.SReturnOne();
    }
}

class MyClass: IStaticVirtual<MyClass>, IStaticAbstract
{
    // AReturnOne是抽象方法, 必须实现
    public static int AReturnOne()
    {
        
        return 11;
    }
}

[TestClass]
public class InterfaceTest
{
    [TestMethod]
    public void TestStaticFunction()
    {
        Assert.AreEqual(1, IStaticFunc.ReturnOne());
    }

    [TestMethod]
    public void TestStaticAbstractFunction()
    {
        Assert.AreEqual(11, MyClass.AReturnOne());
    }
    [TestMethod]
    public void TestIStaticVirtual()
    {
        // 调用默认的SReturnOne实现, 如果MyClass重写SReturnOne, 将调用子类的SReturnOne
        Assert.AreEqual(1, IStaticVirtual<MyClass>.SReturnTwo());
    }
}