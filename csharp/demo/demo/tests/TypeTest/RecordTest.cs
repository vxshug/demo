namespace Demo.tests.TypeTest;

/**
 * record 修饰符定义一个引用类型，用来提供用于封装数据的内置功能。
 * C# 10 允许 record class 语法作为同义词来阐明引用类型，
 * 并允许 record struct 使用相同功能定义值类型。
 *
 * 在记录上声明主构造函数时，编译器会为主构造函数参数生成公共属性。
 */

record RecordPerson(string FirstName, string LastName);
/**
record RecordPerson
{
    public required string FirstName { get; init; }
    public required string LastName { get; init; }
};
 */

record class RecordClassPerson(string FirstName, string LastName);
/**
public record class RecordClassPerson
{
    public string FirstName { get; init; }
    public string LastName { get; init; }
};
 */

record struct RecordStructPerson(string FirstName, string LastName);
/**
public record class RecordStructPerson
{
    public string FirstName { get; set; }
    public string LastName { get; set; }
};
 */

/**
 * - 对于 class 类型，如果两个对象引用内存中的同一对象，则这两个对象相等。
 * - 对于 struct 类型，如果两个对象是相同的类型并且存储相同的值，则这两个对象相等。
 * - 对于具有 record 修饰符（record class、record struct 和 readonly record struct）的类型，如果两个对象是相同的类型并且存储相同的值，则这两个对象相等。
 */
[TestClass]
public class RecordTest
{
    [TestMethod]
    public void TestRecord()
    {
        var person = new RecordPerson("shug", "last");
        var personRef = person;
        Assert.AreEqual("shug", person.FirstName);
        // ReferenceEquals比较两个引用是否指向同一个地址
        Assert.IsTrue(Object.ReferenceEquals(person, personRef));
    }

    [TestMethod]
    public void TestRecordClassPerson()
    {
        var person = new RecordClassPerson("shug", "last");
        var personRef = person;
        Assert.IsTrue(Object.ReferenceEquals(person, personRef));
    }

    [TestMethod]
    public void TestRecordStructPerson()
    {
        var person = new RecordStructPerson("shug", "last");
        var personRef = person;
        // 值类型发生装箱, 引用的对象不一样
        Assert.IsFalse(Object.ReferenceEquals(person, personRef));
    }
}