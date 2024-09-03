using System.Reflection;

namespace Demo.tests;

/**
 * 反射通常和标签一起属性
 */

// 定义标签在那些地方可以使用
// AllowMultiple = true 同一个位置允许多个
[AttributeUsage(AttributeTargets.Class | AttributeTargets.Struct | AttributeTargets.Interface | AttributeTargets.Method, AllowMultiple = true)]
public class ReflectionAttribute : Attribute
{
    private string Label;
    public int Value;

    public ReflectionAttribute(string label)
    {
        Label = label;
        Value = 1;
    }
    public string GetLabel() => Label;
}

[Reflection("ReflectionTarget", Value = 2)]
class ReflectionTarget
{
    public int Value { get; }
    public int Filed;
    
    private int PrivateField;
}

[TestClass]
public class ReflectionTest
{
    [TestMethod]
    public void GetTypeInfo()
    {
        // 反射都需要获取Type类型
        // 1. 通过类名
        Type type1 = typeof(ReflectionTarget);
        var target = new ReflectionTarget();
        // 2. 通过对象
        Type type2 = target.GetType();
        // 3. 通过字符串
        Type? type3 = Type.GetType("Demo.tests.ReflectionTarget");

    }

    [TestMethod]
    public void TestCreateObject()
    {
        Type type = typeof(ReflectionTarget);
        // 反射创建对象
        object? target = Activator.CreateInstance(type);
    }

    [TestMethod]
    public void TestPropertyOrField()
    {
        Type type = typeof(ReflectionTarget);
        PropertyInfo? info = type.GetProperty("Value");
        Console.WriteLine(info);
        
        FieldInfo? field = type.GetField("Filed");
        Console.WriteLine(field);
        
        // 获取私有字段
        FieldInfo? field2 = type.GetField("PrivateField", BindingFlags.NonPublic | BindingFlags.Instance);
        Console.WriteLine(field2);
    }
    
    [TestMethod]
    public void TestGetAttribute()
    {
        var type = typeof(ReflectionTarget);
        var attrs = Attribute.GetCustomAttributes(type);
        foreach (var attr in attrs)
        {
            if (attr is ReflectionAttribute a)
            {
                Console.WriteLine($"label is {a.GetLabel()}");
            }
        }
    }
}