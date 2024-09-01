namespace Demo.tests
{
 using System; // 可以省略System的空间名称

 namespace MyNamespace // 嵌套namespace
 {
  class MyClass
  {
   
  }
 }
 
 /**
 * C# 程序由一个或多个文件组成。 每个文件均包含零个或多个命名空间。
 * 一个命名空间包含类、结构、接口、枚举、委托等类型或其他命名空间。
 */

 public class NamespaceTest
 {
  class MyClass1 : MyNamespace.MyClass // 添加空间名称访问
  {
   
  }
 }
}



