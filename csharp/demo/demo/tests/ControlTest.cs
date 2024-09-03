namespace Demo.tests;

[TestClass]
public class ControlTest
{
    [TestMethod]
    public void TestIf()
    {
        int value = 0;
        if (value == 0) // value == 0 返回true, 执行{ .. }中的代码
        {
            value = 10;
        }
        Assert.AreEqual(10, value);
    }

    [TestMethod]
    public void TestWhile()
    {
        int count = 0;
        int value = 0;
        while (value < 10)
        {
            count++; // 循环次数
            value++; // 修改value
        }
        Assert.AreEqual(10, count);

        count = 0;
        value = 0;
        do // 先执行一次do中的代码, 再判断
        {
            count++;
        } while (value < 0);
        
        Assert.AreEqual(1, count);

        count = 0;
        for (int i = 0; i < 10; i++)
        {
            count++;
        }
        Assert.AreEqual(10, count);
    }
}