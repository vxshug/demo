namespace Demo.tests.TypeTest;

/**
 * 可为 null 值类型 T? 表示其基础值类型 T 的所有值及额外的 null 值。
 * 例如，可以将以下三个值中的任意一个指定给 bool? 变量：true、false 或 null。
 * 基础值类型 T 本身不能是可为空的值类型。
 *
 * 任何可为空的值类型都是泛型 System.Nullable<T> 结构的实例。
 */
[TestClass]
public class NullTest
{
    [TestMethod]
    public void TestNull()
    {
        int? nullInt = 1;
        if (nullInt is int i) // 检测是否为null
        {
            Assert.AreEqual(i, 1);
        }
        int nullInt2 = nullInt ?? 0; // 如果nullInt为null, 使用默认值0
        Assert.AreEqual(nullInt2, 1);
    }
}