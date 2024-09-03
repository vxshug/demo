namespace Demo.tests.TypeTest;

/**
 * 迭代器
 * yield return: 提供迭代中的下一个值
 * yield break :明确表示迭代结束
 */
[TestClass]
public class IteratorTest
{
    IEnumerable<int> ProduceEvenNumbers(int upto)
    {
        for (int i = 0; i < upto; i++)
        {
            yield return i;
        }
    }
    
    IEnumerable<int> TakeWhilePositive(int upto)
    {
        for (int i = 1; i < upto; i++)
        {
            if (i % 2 == 0)
            {
                yield break; // 终止迭代器
            }
            yield return i;
        }
    }
    
    [TestMethod]
    public void TestIterator()
    {
        var evenNumbers = ProduceEvenNumbers(10);
        var enumerator = evenNumbers.GetEnumerator();
        enumerator.MoveNext(); // 获取第一个值
        Assert.AreEqual(0, enumerator.Current);
        Assert.IsTrue(enumerator.MoveNext());
        Assert.AreEqual(1, enumerator.Current);
        Assert.AreEqual(10, evenNumbers.Count());
    }
    
    [TestMethod]
    public void TestIteratorBreak()
    {
        var evenNumbers = TakeWhilePositive(10);
        var enumerator = evenNumbers.GetEnumerator();
        enumerator.MoveNext(); // 获取第一个值
        Assert.AreEqual(1, enumerator.Current);
        Assert.IsFalse(enumerator.MoveNext()); // 已经终止迭代器
        Assert.AreEqual(1, enumerator.Current); // Current不会更新
    }
    
    async Task<int> ProduceNumberAsync(int seed)
    {
        await Task.Delay(100);
        return 2 * seed;
    }

    // 异步迭代器
    async IAsyncEnumerable<int> GenerateNumbersAsync(int count)
    {
        for (int i = 0; i < count; i++)
        {
            yield return await ProduceNumberAsync(i);
        }
    }


    [TestMethod]
    public async Task TestIteratorAsync()
    {
        await foreach (int n in GenerateNumbersAsync(5)) // 使用异步迭代器
        {
            Console.Write(n);
            Console.Write(" ");
        }

        var asyncEnumerable = GenerateNumbersAsync(3);
        var asyncEnumerator = asyncEnumerable.GetAsyncEnumerator();
        await asyncEnumerator.MoveNextAsync(); // 获取第一个值
        Assert.AreEqual(0, asyncEnumerator.Current);
        await asyncEnumerator.MoveNextAsync();
        Assert.AreEqual(2, asyncEnumerator.Current);
    }
}