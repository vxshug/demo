namespace Demo.tests.TypeTest;

struct Point
{
    public int X { get; set; }
    public int Y { get; set; }
    
    public readonly int Sum => X + Y; // readonly修饰方法, 表示不会修改变量
    
    // public readonly void Increment() => X++; 报错, readonly的方法不能修改值
    public Point(int x, int y)
    {
        X = x;
        Y = y;
    }
}

/**
 * 任何字段声明都必须具有 readonly 修饰符
 * 何属性（包括自动实现的属性）都必须是只读的或init。 请注意，init从C# 版本 9 开始可用。
 */
readonly struct ReadOnlyPoint
{
    // 不能有set
    public int X { get; }
    public int Y { get; init; } = 1; // 默认值
    

    public ReadOnlyPoint(int x, int y)
    {
        X = x;
        Y = y;
    }

    public ReadOnlyPoint(int x)
    {
        X = x;
    }
}

/**
 * 由于结构类型具有值语义，因此建议定义不可变的结构类型。
 */
[TestClass]
public class StructTest
{
    [TestMethod]
    public void TestStruct()
    {
        var p = new Point(1, 2);
        var p2 = p; // 复制一份Point
        p.X = 3; // 不会影响p2
        Assert.AreNotEqual(p.X, p2.X); 
        Assert.AreEqual(3, p.X);
        Assert.AreEqual(1, p2.X); // p2.X 不变
    }

    [TestMethod]
    public void TestDefault()
    {
        var p = new ReadOnlyPoint(10, 2);
        var p2 = new ReadOnlyPoint(3); // Y使用默认值1
        Assert.AreEqual(1, p2.Y);
        // p.Y = 1; // 不能修改p.Y
    }

    [TestMethod]
    public void TestWith()
    {
        var p = new ReadOnlyPoint(10, 3);
        var p2 = p with { Y = 4 }; // 通过with覆盖属性
        Assert.AreEqual(4, p2.Y);
        Assert.AreEqual(10, p2.X);
    }
}