using System.Collections;
using System.Collections.Concurrent;
using System.Collections.Immutable;
using System.Collections.ObjectModel;

namespace Demo.tests;

/**
 * 集合类型：泛型集合和非泛型集合。
 * 泛型集合在编译时是类型安全的。 因此，泛型集合通常能提供更好的性能。 构造泛型集合时，它们接受类型形参；并在向该集合添加项或从该集合删除项时无需在 Object 类型间来回转换。
 * 非泛型集合将项存储为 Object，需要强制转换
 */
[TestClass]
public class CollectionsTest
{
    [TestMethod]
    public void TestList()
    {
        // 按索引访问的集合
        List<int> list = [1, 2, 3]; // 泛型集合
        int sum = 0;
        list.Add(4); // 添加元素到列表
        foreach (var i in list)
        {
            sum += i;
        }
        Assert.AreEqual(10, sum);
        
        // 非范型
        Array array = new[] { 1, 2, 3, 4, 5 };
        sum = 0;

        foreach (var a in array)
        {
            sum = (int)a + sum;
        }
        Assert.AreEqual(15, sum);
        
        // 非范型
        ArrayList arrayList = new ArrayList();
        arrayList.Add(1);
        arrayList.Add(2);
        sum = 0;

        foreach (var a in arrayList)
        {
            sum = (int)a + sum;
        }
        Assert.AreEqual(3, sum);
        
        // 依赖 dotnet add package System.Collections.Immutable
        // 不可变
        ImmutableArray<int> immutableArray = ImmutableArray.Create(1, 2, 3, 4, 5); //添加不会影响原ImmutableArray, 会返回新的ImmutableArray

        sum = 0;
        foreach (var a in immutableArray)
        {
            sum = a + sum;
        }
        Assert.AreEqual(15, sum);
        
        ImmutableList<int> immutableList = ImmutableList.Create(1, 2, 3, 4, 5);
        
        sum = 0;
        foreach (var a in immutableList)
        {
            sum = a + sum;
        }
        Assert.AreEqual(15, sum);
    }

    [TestMethod]
    public void TestFIFO()
    {
        // 先进先出
        // 范型
        Queue<int> queue = new Queue<int>();
        queue.Enqueue(1);
        queue.Enqueue(2);
        CollectionAssert.AreEqual(new List<int> { 1, 2 }, queue.ToList());
        
        // 非范型
        Queue queueFifo = new Queue();
        queueFifo.Enqueue(1);
        queueFifo.Enqueue(2);
        CollectionAssert.AreEqual(new List<int> { 1, 2 }, queueFifo.ToArray());

        // 线程安全
        ConcurrentQueue<int> concurrentQueue = new ConcurrentQueue<int>();
        concurrentQueue.Enqueue(1);
        concurrentQueue.Enqueue(2);
        CollectionAssert.AreEqual(new List<int> { 1, 2 }, concurrentQueue.ToArray());

        // 不可变
        ImmutableQueue<int> immutableQueue = ImmutableQueue.Create(1, 2, 3, 4, 5);
        CollectionAssert.AreEqual(new List<int> { 1, 2, 3, 4, 5 }, immutableQueue.ToArray());
    }

    [TestMethod]
    public void TestLIFO()
    {
        // 先进后出
        // 范型
        Stack<int> stack = new Stack<int>();
        stack.Push(1);
        stack.Push(2);
        CollectionAssert.AreEqual(new List<int> { 2, 1 }, stack.ToArray());
        
        // 非范型
        Stack stackLifo = new Stack();
        stackLifo.Push(1);
        stackLifo.Push(2);        
        CollectionAssert.AreEqual(new List<int> { 2, 1 }, stackLifo.ToArray());

        // 线程安全
        ConcurrentStack<int> concurrentStack = new ConcurrentStack<int>();
        concurrentStack.Push(1);
        concurrentStack.Push(2);
        CollectionAssert.AreEqual(new List<int> { 2, 1 }, concurrentStack.ToArray());

        // 不可变
        ImmutableStack<int> immutableStack = ImmutableStack.Create(1, 2, 3, 4, 5);
        CollectionAssert.AreEqual(new List<int> { 5, 4, 3, 2, 1 }, immutableStack.ToArray());
    }

    [TestMethod]
    public void TestSet()
    {
        // 集合
        HashSet<int> set = new HashSet<int> { 1, 2, 1 };  // 重复项会去掉
        Assert.AreEqual(3, set.Sum());
        
        SortedSet<int> sortedSet = new SortedSet<int> { 1, 2, 1 };  // 排序的集合
        Assert.AreEqual(3, sortedSet.Sum());
        
        // 不可变的集合
        ImmutableSortedSet<int> immutableSortedSet = ImmutableSortedSet.Create(1, 2, 3);
        Assert.AreEqual(6, immutableSortedSet.Sum());
        
        ImmutableHashSet<int> immutableHashSet = ImmutableHashSet.Create(1, 2, 3);
        Assert.AreEqual(6, immutableHashSet.Sum());
    }

    [TestMethod]
    public void TestSortedList()
    {
        // 排序的列表
        // 范型
        SortedList<int, int> sortedList = new SortedList<int, int> { {1, 2}, {3, 4} };
        int sum = 0;
        foreach (var i in sortedList)
        {
            sum += i.Value;
        }
        Assert.AreEqual(6, sum);
        
        // 非范型
        SortedList sortedList1 = new SortedList { {1, 2}, {3, 4} };
        sum = 0;
        foreach (var i in sortedList1)
        {
            if (i is DictionaryEntry entry)
            {
                sum += (int)entry.Value;
            }
        }
        Assert.AreEqual(6, sum);
        
        // 不可变
        ImmutableSortedDictionary<int, int> immutableSortedDictionary = ImmutableSortedDictionary.Create<int, int>();
        immutableSortedDictionary.Add(1, 2); // 添加不会影响原ImmutableSortedDictionary, 会返回新的ImmutableSortedDictionary
        immutableSortedDictionary.Add(2, 3);
        sum = 0;
        foreach (var i in immutableSortedDictionary)
        {
            sum += i.Value;
        }
        Assert.AreEqual(0, sum);
    }

    [TestMethod]
    public void TestHashTable()
    {
        // 映射
        // 范型
        Dictionary<int, int> dictionary = new Dictionary<int, int> { {1, 2}, {3, 4} };
        CollectionAssert.AreEqual( new List<int> { 2, 4 }, dictionary.Values.ToList());
        
        // 非范型
        int sum = 0;
        Hashtable hashtable = new Hashtable() { {1, 2}, {3, 4} };
        foreach (var hashtableValue in hashtable.Values)
        {
            sum = (int)hashtableValue + sum;
        }
        Assert.AreEqual(6, sum);
        
        // 线程安全
        ConcurrentDictionary<int, int> concurrentDictionary = new ConcurrentDictionary<int, int>();
        concurrentDictionary.TryAdd(1, 2);
        concurrentDictionary.TryAdd(2, 3);
        CollectionAssert.AreEqual(new List<int> { 2, 3 }, concurrentDictionary.Values.ToList());

        // ReadOnlyDictionary是对Dictionary的一层包装, 实际数据存储在内部的Dictionary
        ReadOnlyDictionary<int, int> readOnlyDictionary = new ReadOnlyDictionary<int, int>(concurrentDictionary);
        CollectionAssert.AreEqual(new List<int> { 2, 3 }, readOnlyDictionary.Values.ToList());

        ImmutableDictionary<int, int> immutableDictionary = ImmutableDictionary<int, int>.Empty
            .Add(1, 2)
            .Add(2, 3);
        CollectionAssert.AreEqual(new List<int> { 2, 3 }, immutableDictionary.Values.ToList());
    }
}