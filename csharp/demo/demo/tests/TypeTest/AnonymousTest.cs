namespace Demo.tests.TypeTest;

/**
 * 匿名类型提供了一种方便的方法，可用来将一组只读属性封装到单个对象中，而无需首先显式定义一个类型。
 * 类型名由编译器生成，并且不能在源代码级使用。 每个属性的类型由编译器推断。
 */
[TestClass]
public class AnonymousTest
{
    [TestMethod]
    public void TestAnonymous()
    {
        // 创建匿名类型
        var anonymous = new { Id = 1, Name = "shug" };
        Assert.AreEqual("shug", anonymous.Name);
        // 复用匿名类型
        var anonymous2 = anonymous with { Id = 2 };
        Assert.AreEqual("shug", anonymous2.Name);
        Assert.AreEqual(2, anonymous2.Id);
    }
}