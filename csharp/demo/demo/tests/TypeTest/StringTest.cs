namespace Demo.tests.TypeTest;

/**
 * string 类型表示零个或多个 Unicode 字符的序列。 string 是 System.String 在 .NET 中的别名。
 */
[TestClass]
public class StringTest
{
    [TestMethod]
    public void TestConcat()
    {
        var string1 = "Hello World";
        var string2 = "Hello World";
        var string3 = string1 + string2;
        Assert.AreEqual("Hello WorldHello World", string3);
    }

    [TestMethod]
    public void TestLiteral()
    {
        // 可以嵌套特殊符号
        var literal = """
                      多行文本 "
                      """;
        Assert.AreEqual("多行文本 \"", literal);
        
        var string1 = @"Hello \World"; // 不处理转义序列
        Assert.AreEqual("Hello \\World", string1);

        // 内插字符串
        var age = 18;
        var string2 = $"Hello {age}";
        Assert.AreEqual("Hello 18", string2);
    }
}