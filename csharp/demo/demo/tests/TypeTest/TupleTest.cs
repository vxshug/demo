namespace Demo.tests.TypeTest;

/**
 * 元组功能提供了简洁的语法来将多个数据元素分组成一个轻型数据结构。
 * 
 * System.ValueTuple 类型支持的 C# 元组不同于 System.Tuple 类型表示的元组。 主要区别如下：
 * System.ValueTuple 类型是值类型。 System.Tuple 类型是引用类型。
 * System.ValueTuple 类型是可变的。 System.Tuple 类型是不可变的。
 * System.ValueTuple 类型的数据成员是字段。 System.Tuple 类型的数据成员是属性。
 */
[TestClass]
public class TupleTest
{
    [TestMethod]
    public void TestTuple()
    {
        (bool, int) t1 = (true, 10); // 创建元组
        Assert.AreEqual(true, t1.Item1);
        Assert.AreEqual(10, t1.Item2);
        (bool B, int I) t2 = (false, 0); // 指定名称
        Assert.AreEqual(false, t2.B);
        Assert.AreEqual(0, t2.I);
        
        var t3 = (T: true, I: 3);
        Assert.AreEqual(3, t3.I);
        Assert.AreEqual(true, t3.T);
        
        var (A,B) = t3; // 析构
        Assert.AreEqual(3, B);
        Assert.AreEqual(true, A);
    }
}