namespace Demo.tests.FunctionTest;

/**
 * C# 语言的异常处理功能有助于处理在程序运行期间发生的任何意外或异常情况。
 * 异常处理功能使用 try、catch 和 finally 关键字来尝试执行可能失败的操作、在确定合理的情况下处理故障，以及在事后清除资源。
 *
 *  异常是使用 throw 关键字创建而成。
 *
 * - 异常是最终全都派生自 System.Exception 的类型。
 * - 在可能抛出异常的语句周围使用 try 代码块。
 * - 在 try 代码块中出现异常后，控制流会跳转到调用堆栈中任意位置上的首个相关异常处理程序。 在 C# 中，catch 关键字用于定义异常处理程序。
 * - 如果给定的异常没有对应的异常处理程序，那么程序会停止执行，并显示错误消息。
 * - 除非可以处理异常并让应用程序一直处于已知状态，否则不捕获异常。 如果捕获 System.Exception，使用 catch 代码块末尾的 throw 关键字重新抛出异常。
 * - 如果 catch 代码块定义异常变量，可以用它来详细了解所发生的异常类型。
 * - 使用 throw 关键字，程序可以显式生成异常。
 * - 异常对象包含错误详细信息，如调用堆栈的状态和错误的文本说明。
 * - 即使引发异常，finally 代码块中的代码仍会执行。 使用 finally 代码块可释放资源。例如，关闭在 try 代码块中打开的任何流或文件。
 * - .NET 中的托管异常在 Win32 结构化异常处理机制的基础之上实现。
 */

// 自定义异常类
class CustomException : Exception
{
    public CustomException(string message) : base(message)
    {}
}

[TestClass]
public class ExceptionTest
{
    [TestMethod]
    public void TestException()
    {
        try
        {
            // 抛出异常
            throw new CustomException("Custom exception");
        }
        catch (CustomException e)
        {
            Console.WriteLine(e);
            // throw; // 再次抛出捕获的异常
        }
        finally
        {
            // 不管是否有异常都会执行
            Console.WriteLine("Finally");
        }
    }
}