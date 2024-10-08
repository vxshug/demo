namespace Demo.tests;

[TestClass]
public class VariableDefineTest
{
    [TestMethod]
    public void TestNumber()
    {
        sbyte sbyteValue = 1; // -128到128 (带符号的 8 位整数)	
        Assert.AreEqual(sbyteValue, 1);
        
        // 数字类型转换
        byte convertValue = (byte) sbyteValue;
        Assert.AreEqual(convertValue, 1);
        
        byte byteValue = 1; // 0到255 (无符号的 8 位整数)
        Assert.AreEqual(byteValue, 1);
        
        short shortValue = 1; // -32768到32767 (有符号 16 位整数)
        Assert.AreEqual(shortValue, 1);
        
        ushort ushortValue = 1; // 0到65535 (无符号 16 位整数)
        Assert.AreEqual(ushortValue, 1);
        
        int intValue = 1; // -2_147_483_648到2_147_483_647 (带符号的 32 位整数)
        Assert.AreEqual(intValue, 1);
        
        uint uintValue = 1; // 0到4_294_967_295 (有符号的 32 位整数)
        Assert.IsTrue(uintValue == 1);
        
        long longValue = 1; // 带符号的 64 位整数
        Assert.AreEqual(longValue, 1);
        
        ulong ulongValue = 1; // 无符号的 64 位整数
        Assert.IsTrue(ulongValue == 1);
        
        nint nintValue = 1; // 由平台确定位数的带符号整数
        Assert.AreEqual(nintValue, 1);
        
        nuint nuintValue = 1; // 由平台确定位数的无符号整数
        Assert.IsTrue(nuintValue == 1);
        
        float floatValue = 1.1F; // 带f或F后缀的是float
        Assert.AreEqual(floatValue, 1.1F);
        
        double doubleValue = 1.1; // 带后缀d或D, 不带后缀是double
        Assert.AreEqual(doubleValue, 1.1);
        
        decimal decimalValue = 1.1M; // 带m或M后缀的是decimal
        Assert.AreEqual(decimalValue, 1.1M);
        
        bool boolValue = true;
        Assert.IsTrue(boolValue);
        
        char charValue = 'a'; // 16位
        Assert.AreEqual(charValue, 'a');
    }
    
    [TestMethod]
    public void TestVar()
    {
        // var 定义变量可以自动推导类型, 仅适用于局部变量
        var i = 1;
        Assert.AreEqual(1, i);

        // new表达式
        List<int> xs = new();
    }

    /**
     * reference 变量是引用另一个变量（称为引用）的变量。
     * 也就是说，reference 变量是其引用的别名。
     * 向 reference 变量赋值时，该值将分配给引用。
     * 读取 reference 变量的值时，将返回引用的值。
     */
    [TestMethod]
    public void TestReference()
    {
        int i = 1;
        ref int j = ref i;
        j = 2;
        Assert.AreEqual(i, j);
        Assert.AreEqual(2, i);
        
        int newValue = 10;
        j = ref newValue; // 使用j引用新的变量
        
        // 只读引用, 只能读取变量, 不能写入
        ref readonly int readOnlyRef = ref newValue;
        Assert.AreEqual(10, readOnlyRef);
        
        // 可以修改newValue, 但是不能通过readOnlyRef修改
        newValue = 20;
        Assert.AreEqual(20, readOnlyRef);
    }
}