namespace Demo.tests.FunctionTest;

/**
 * 扩展方法是具有特殊用途的静态方法，借助它无需修改你想要为其添加功能的已有原始类型，即可向其添加功能。
 */

public static class Extensions
{
    public static int AddOne(this int i ) => i + 1;
}
[TestClass]
public class ExtensionsTest
{
    [TestMethod]
    public void TestExtensions()
    {
        var i = 1;
        Assert.AreEqual(2, i.AddOne());
    }
}