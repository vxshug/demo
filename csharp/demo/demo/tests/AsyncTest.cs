namespace Demo.tests;

/**
 * 异步编程
 * 返回值:
 *  - Task（对于执行操作但不返回任何值的异步方法）。
 *  - Task<TResult>（对于返回值的异步方法）。
 *  - 任何具有可访问的 GetAwaiter 方法的类型。 GetAwaiter 方法返回的对象必须实现 System.Runtime.CompilerServices.ICriticalNotifyCompletion 接口。
 *  - IAsyncEnumerable<T>（对于返回异步流的异步方法）。
 */
[TestClass]
public class AsyncTest
{
    public async void TestVoid()
    {
        
    }
    public async Task TestTask()
    {
        
    }

    public async Task<int> TestTaskValue()
    {
        return 0;
    }

    public async Task<string> TestCancelTaskValue(CancellationToken token)
    {
        token.ThrowIfCancellationRequested();
        await Task.Delay(1000);
        token.ThrowIfCancellationRequested();
        return string.Empty;
    }

    [TestMethod]
    public async Task TestTask1()
    {
        await TestTask(); // 会依次执行
        await TestTaskValue();
        await TestTask();
    }
    
    [TestMethod]
    public async Task TestTask2()
    {
        CancellationTokenSource cts = new CancellationTokenSource();
        cts.CancelAfter(100);
        try
        {
            await TestCancelTaskValue(cts.Token);
        }
        catch (OperationCanceledException e)
        {
            Console.WriteLine(e);
        }
    }

    /**
     * Task.Run开启任务
     */
    [TestMethod]
    public async Task TestTask3()
    {
        Task.Run(async () =>
        {
            Task.Delay(400).Wait();
            Console.WriteLine("Hello World 3");
        });
        Task.Run(async () =>
        {
            Task.Delay(300).Wait();
            Console.WriteLine("Hello World 2");
        });
        Task.Run(async () =>
        {
            Task.Delay(200).Wait();
            Console.WriteLine("Hello World 1");
        });
        Task.Delay(500).Wait();
    }
}