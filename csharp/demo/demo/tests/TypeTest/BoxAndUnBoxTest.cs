namespace Demo.tests.TypeTest;

/**
 * 装箱是将值类型转换为 object 类型或由此值类型实现的任何接口类型的过程。
 * 取消装箱将从对象中提取值类型。
 * 装箱是隐式的；取消装箱是显式的。
 *
 * 装箱和取消装箱过程需要进行大量的计算。
 * 对值类型进行装箱时，必须分配并构造一个新对象。
 * 取消装箱所需的强制转换也需要进行大量的计算，只是程度较轻。
 */
[TestClass]
public class BoxAndUnBoxTest
{
    public void TestBoxAndUnBox()
    {
        int i = 1; 
        object o1 = i; // 装箱
        
        int i2 = (int)o1; // 取消装箱
    }
}