namespace Demo.tests.LINQTest;

/**
 * 查询可能会执行三种操作之一:
 * - 检索元素的子集以生成新序列，而不修改各个元素。
 * - 如前面的示例所示检索元素的序列，但是将它们转换为新类型的对象。
 * - 检索有关源数据的单独值，如: 与特定条件匹配的元素数, 具有最大或最小值的元素。与某个条件匹配的第一个元素，或指定元素集中特定值的总和。
 */

[TestClass]
public class QueryExpresionTest
{
    // 检索元素的子集以生成新序列，而不修改各个元素。
    [TestMethod]
    public void TestNew()
    {
        var numbers = new List<int> { 1, 2, 3, 4, 5 };
        var evenNumbers = from number in numbers
                        where (number % 2) == 0
                        select number;
    }

    // 转换为新类型的对象。
    [TestMethod]
    public void TestNewMap()
    {
        var numbers = new List<int> { 1, 2, 3, 4, 5 };
        var evenNumbers = from number in numbers
                        where (number % 2) == 0
                        select $"even {number}";
    }
    
    // 检索有关源数据的单独值
    [TestMethod]
    public void TestSingle()
    {
        var numbers = new List<int> { 1, 2, 3, 4, 5 };
        var evenNumbers = from number in numbers
                        where (number % 2) == 0
                        select number;
        Assert.AreEqual(2, evenNumbers.Count());
        Assert.AreEqual(4, evenNumbers.Max());
        Assert.AreEqual(2, evenNumbers.Min());
        Assert.AreEqual(2, evenNumbers.First());
        Assert.AreEqual(4, evenNumbers.Last());
        Assert.AreEqual(6, evenNumbers.Sum());
    }
    
    // 查询表达式必须以 from 子句开头，且必须以 select 或 group 子句结尾。
    // 在第一个 from 子句与最后一个 select 或 group 子句之间，可以包含以下这些可选子句中的一个或多个：where、orderby、join、let，甚至是其他 from 子句。
    // 还可以使用 into 关键字，使 join 或 group 子句的结果可以充当相同查询表达式中的更多查询子句的源。
    // 
}