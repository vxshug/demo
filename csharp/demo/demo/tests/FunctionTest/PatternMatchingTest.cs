namespace Demo.tests.FunctionTest;

/**
 * "模式匹配" 是一种测试表达式是否具有特定特征的方法。
 * "is 表达式" 目前支持通过模式匹配测试表达式并有条件地声明该表达式结果。
 * "switch 表达式" 允许你根据表达式的首次匹配模式执行操作。 
 */
[TestClass]
public class PatternMatchingTest
{
    [TestMethod]
    public void TestNullCheck()
    {
        int? value = null;
        if (value is int nu0)
        {
            Assert.AreEqual(0, nu0);
        }
        if (value is {} nu1)
        {
            Assert.AreEqual(0, nu1);
        }
        // 这里也可以访问到nu0和nu1, 只是可以没有初始化
    }

    [TestMethod]
    public void TestSwitch()
    {
        int value = 1;
        var ret = value switch // 使用switch匹配
        {
            0 => 0,
            _ => 1
        };
        Assert.AreEqual(1, ret);
        
        var ret2 = value switch
        {
            < 0 => "neg",
            > 0 => "positive",
            0 => "zero",
        };
        Assert.AreEqual("positive", ret2);

        var doubleValue = (false, 1);
        var ret3 = doubleValue switch
        {
            { Item1: false, Item2: 1 } => 1,
            { Item1: false, Item2: 2 } => 2,
            { Item1: true } => 0, // 忽略Item2
        };
        Assert.AreEqual(1, ret3);
        
        // 弃元
        var arr = new[] { 1, 2, 3, 4, 5 };
        var rt = arr switch
        {
            [_, 1, var i] => "1", // _ 表示跳过当前项
            [_, 1, .., var i] => "2", // ... 跳过中间项
            _ => "0"
        };
        Assert.AreEqual("0", rt);
    }
}