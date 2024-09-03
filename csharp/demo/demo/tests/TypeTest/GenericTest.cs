using System.Numerics;

namespace Demo.tests.TypeTest;

/**
 * 泛型支持设计类和方法,
 * 在编译过程中将泛型类型参数替换为类型参数.
 * 泛型通常与集合以及作用于集合的方法一起使用。 System.Collections.Generic 命名空间包含几个基于泛型的集合类。 不建议使用非泛型集合.
 */

class GenericClass<T> where T : INumber<int> // T 为泛型类型参数, 名称T时任意的, where可以限制范型
{
    public T Number { get; set; }
}

class GenericTestFunction
{
 // 范型方法, 也可以使用where限制T
    public T Function<T>(T input)
    where T : INumber<int>
    {
        return input;
    }
}

[TestClass]
public class GenericTest
{
    [TestMethod]
    public void TestGenericClass()
    {
        var c1 = new GenericClass<int>();
        c1.Number = 1;
        Assert.AreEqual(1, c1.Number);
    }

    [TestMethod]
    public void TestGenericFunction()
    {
        var c1 = new GenericTestFunction();
        Assert.AreEqual(1, c1.Function<int>(1));
    }
}