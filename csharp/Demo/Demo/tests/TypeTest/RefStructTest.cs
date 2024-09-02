namespace Demo.tests.TypeTest;

/**
 * ref struct 类型的实例是在堆栈上分配的，不能转义到托管堆。
 * ref struct的限制:
 * - 不能是数组的元素类型。
 * - 不能是类或非 ref struct 的字段的声明类型。
 * - 不能被装箱为 System.ValueType 或 System.Object。
 * - 变量不能在 Lambda 表达式或本地函数中捕获。
 * - 在 C# 13 之前，ref struct 变量无法在 async 方法中使用。 从 C# 13 开始，ref struct 变量不能在 async 方法中和 await 表达式用于相同的块。 但是，可以在同步方法中使用 ref struct 变量
 * - 在 C# 13 之前，ref struct 变量不能在迭代器中使用。 从 C# 13 开始，ref struct 类型和 ref 局部变量可以在迭代器中使用，前提是它们不在代码段中具有 yield return 语句。
 * - 在 C# 13 之前，ref struct 无法实现接口。 从 C# 13 开始，ref 结构可实现接口，但必须遵循 ref 安全性规则。
 * - 在 C# 13 之前，ref struct 不能是类型参数。 从 C# 13 开始，ref struct 可以是类型参数
 */

public ref struct CustomRef
{
    public bool IsValid;
    public int Age;

    public readonly ref int ReadOnlyRef; // 只能在构造函数或 init 访问器中使用 = ref 运算符通过 ref 重新赋值此类字段。 可以在字段访问修饰符允许的任何时间点使用 = 运算符分配值。
    public ref readonly int RefReadOnly;  // 在任何时候，都不能使用 = 运算符为此类字段赋值。 但是，可以使用 = ref 运算符通过 ref 重新赋值字段。                                                                                                                                                                                                                                                                      
    public readonly ref readonly int RefReadOnlyRef; // 只能在构造函数或 init 访问器中通过 ref 重新赋值此类字段。

    public CustomRef(bool isValid, ref int age)
    {
        // 初始化时可以修改readonly ref的指向
        ReadOnlyRef = ref age; // 引用本身不能改变
        RefReadOnly = ref age; // 可以修改引用
        // RefReadOnly = age; // 不能修改引用的值
        RefReadOnlyRef = ref age; // 不能修改引用和引用的值
        // RefReadOnlyRef = age; // 不能修改引用的值
        IsValid = isValid;
        Age = age;
    }
}

// 将 ref struct 声明为 readonly, readonly 修饰符必须位于 ref 修饰符之前
public readonly ref struct ConversionRequest
{
    public ConversionRequest(double rate, ReadOnlySpan<double> values)
    {
        Rate = rate;
        Values = values;
    }

    public double Rate { get; }
    public ReadOnlySpan<double> Values { get; }
}

interface IRefStruct
{
    
}

ref struct RefStruct;



[TestClass]
public class RefStructTest
{
    [TestMethod]
    public void TestRefStruct()
    {
        var boolValue = false;
        var intValue = 0;
        var newValue = 1;
        var myRef = new CustomRef(boolValue, ref intValue);
        myRef.IsValid = true;
        Assert.AreEqual(false, boolValue);
        myRef.ReadOnlyRef = 1; // 可以修改引用的值
        // myRef.ReadOnlyRef = ref newValue; // 不可以修改引用
        Assert.AreEqual(1, intValue);

        myRef.RefReadOnly = ref newValue; // 可以修改引用
        // myRef.RefReadOnly = 10; // 不可以修改引用的值
        
        // myRef.RefReadOnlyRef = ref newValue; // 不可以修改引用
        // myRef.RefReadOnlyRef = 11; // 不可以修改引用的值
    }

    [TestMethod]
    public void TestRefStructInterface()
    {
        var refStruct = new RefStruct();
        // IRefStruct refStructInterface = refStruct; // 无法将 ref struct 转换为它实现的接口的实例。
    }
}