namespace Demo.tests.TypeTest;

/**
 *
 * 程序集是通过在单个编译中编译一个或多个 .cs 文件而创建的 .dll 或 .exe
 * 访问修饰符:
 * public: 任何程序集中的代码都可以访问此类型或成员。
 * protected internal: 只有同一程序集中的代码或另一个程序集的派生类中的代码才能访问此类型或成员。
 * protected: 只有同一 class 或派生的 class 中的代码才能访问此类型或成员。
 * internal: 只有同一程序集中的代码才能访问此类型或成员。
 * private protected: 只有同一程序集和同一类/派生类中的代码才能访问该类型或成员。
 * private: 只有在同一 class 或 struct 中声明的代码才能访问此成员。(默认值)
 * file: 只有同一文件中的代码可以访问类型或成员。
 *
 * 一个类可包含下列成员的声明:
 * - 构造函数
 * - 常量
 * - 字段
 * - 终结器
 * - 方法
 * - 属性
 * - 索引器
 * - 运算符
 * - 事件
 * - 委托
 * - 类
 * - 结构类型
 * - 枚举类型
 */

class Template(string Label) // 主构造函数
{
    // 次构造函数
    public Template(): this("lebel") // 调用主构造函数
    {
    }

    // 常量
    public const int P1 = 1;
    // 字段
    private int f1 = 1;
    // 终结器
    ~Template()
    {
        
    }
    // 方法
    void Function()
    {}
    // 属性
    public string Ok { get; set; }
    // 索引器
    public int this[int i]
    {
        get => i;
        set => f1 = value;
    }

    // 运算符
    public static Template operator +(Template l1, Template l2)
    {
        return l1;
    }
    // 事件
    public event Action<string> OnMessageReceived;
    // 委托
    public delegate int PerformCalculation(int x, int y);
    // 类
    class Inner
    {
    }
    // 接口
    interface IInterface
    {
    }
    // 结构类型
    struct MyStruct
    {
    }
    // 枚举类型
    enum Season
    {
        Spring,
        Summer,
        Autumn,
        Winter
    }
}

class ChildTemplate(string Label): Template
{
}

// 定义抽象类, 抽象类不能实例化
abstract class AbParent
{
    // 抽象方法, 可以不实现
    public abstract int Function();
}

class AbImp: AbParent
{
    public override int Function()
    {
        return 1;
    }
}

class VirtualParent
{
    // virtual函数要有默认实现
    public virtual int Function()
    {
        return 0;
    }
}

class InhertVirtual: VirtualParent
{
    public override int Function()
    {
        // 在基类的返回值上加1
        return base.Function() + 1;
    }
}

[TestClass]
public class ClassTest
{
    [TestMethod]
    public void TestAbstract()
    {
        var impl = new AbImp();
        Assert.AreEqual(1, impl.Function());
    }

    [TestMethod]
    public void TestVirtual()
    {
        var vp = new VirtualParent();
        var vpc = new InhertVirtual();
        Assert.AreEqual(0, vp.Function());
        Assert.AreEqual(1, vpc.Function());
    }
}