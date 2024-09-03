using System.Runtime.InteropServices.JavaScript;

namespace Demo.tests.TypeTest;

/**
 * C# 是一种强类型语言。 每个变量和常量都有一个类型，每个求值的表达式也是如此。
 * 每个方法声明都为每个输入参数和返回值指定名称、类型和种类（值、引用或输出）。
 * .NET 类库定义了内置数值类型和表示各种构造的复杂类型。
 * 其中包括文件系统、网络连接、对象的集合和数组以及日期。
 * 典型的 C# 程序使用类库中的类型，以及对程序问题域的专属概念进行建模的用户定义类型。
 *
 * 类型中可存储的信息包括以下项：
 * - 类型变量所需的存储空间。
 * - 可以表示的最大值和最小值。
 * - 包含的成员（方法、字段、事件等）。
 * - 继承自的基类型。
 * - 实现的接口。
 * - 允许执行的运算种类。
 *  
 * 
 */
[TestClass]
public class OverviewTest
{
    /**
     * C#支持继承原则。 类型可以派生自其他类型（称为基类型）。
     * 派生类型继承（有一些限制）基类型的方法、属性和其他成员。
     * 基类型可以继而从某种其他类型派生，在这种情况下，派生类型继承其继承层次结构中的两种基类型的成员。
     * 所有类型（包括 System.Int32 (C# keyword: int) 等内置数值类型）最终都派生自单个基类型，即 System.Object (C# keyword: object)。
     * 这样的统一类型层次结构称为通用类型系统 (CTS)。
     *
     * CTS 中的每种类型被定义为值类型或引用类型。 这些类型包括 .NET 类库中的所有自定义类型以及用户定义类型。
     * 使用 struct 关键字定义的类型是值类型；所有内置数值类型都是 structs。
     * 使用 class 或 record 关键字定义的类型是引用类型。
     * 引用类型和值类型遵循不同的编译时规则和运行时行为。
     *
     *                      System.Object
     *                       /          \
     *             System.ValueType      引用类型(System.String等)
     *             /        |
     *          结构体    System.Enum
     *                       |
     *                      枚举
     *
     *  C# 9 添加记录(record)
     * 类是引用类型。 创建类型的对象后，向其分配对象的变量仅保留对相应内存的引用。 将对象引用分配给新变量后，新变量会引用原始对象。 通过一个变量所做的更改将反映在另一个变量中，因为它们引用相同的数据。
     * 结构是值类型。 创建结构时，向其分配结构的变量保留结构的实际数据。 将结构分配给新变量时，会复制结构。 因此，新变量和原始变量包含相同数据的副本（共两个）。 对一个副本所做的更改不会影响另一个副本。
     * 记录类型可以是引用类型 (record class) 或值类型 (record struct)。
     */ 
    [TestMethod]
    public void TestTypeSystem()
    {
        // Dictionary是引用类型
        var dictionary = new Dictionary<string, string> { {"A", "A"} };
        var dictionaryRef = dictionary; // 引用
        dictionaryRef["A"] = "Hello World Again";
        CollectionAssert.AreEqual(dictionary, dictionaryRef);
        Assert.AreEqual("Hello World Again", dictionary["A"]);
    }

    [TestMethod]
    public void TestDynamic()
    {
        // dynamic 类型表示变量的使用和对其成员的引用绕过编译时类型检查。 
        // 编译器不会对包含类型 dynamic 的表达式的操作进行解析或类型检查。
        // 编译器将有关该操作信息打包在一起，之后这些信息会用于在运行时评估操作。
        // 在此过程中，dynamic 类型的变量会编译为 object 类型的变量。
        // 因此，dynamic 类型只在编译时存在，在运行时则不存在。
    }
}