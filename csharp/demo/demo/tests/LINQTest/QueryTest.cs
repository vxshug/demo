namespace Demo.tests.LINQTest;

/**
 * 所有 LINQ 查询操作都由以下三个不同的操作组成：
 * - 获取数据源。
 * - 创建查询。
 * - 执行查询。
 *
 * 支持IEnumerable, IEnumerable<T> 或派生接口（如泛型 IQueryable<T>）的类型称为可查询类型 。
 * 可查询类型不需要进行修改或特殊处理就可以用作 LINQ 数据源。
 */

[TestClass]
public class QueryTest
{
    [TestMethod]
    public void TestSetup()
    {
        // 创建数据源
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        
        // 创建查询
        var numberQuery =
                        from number in numbers // 指定数据源
                        where (number % 2) == 0 // 应用筛选器
                        select number;  // 指定返回数据的类型
        // 执行查询
        var list = numberQuery.ToList();
        CollectionAssert.AreEqual(new List<int>() {2,4,6,8,10,12,14}, list);
    }

    // 立即执行指的是读取数据源并执行一次运算。 返回标量结果的所有标准查询运算符都立即执行。 
    // Count、Max、Average 和 First 就属于此类查询。
    // 要强制立即执行任何查询并缓存其结果，可调用 ToList 或 ToArray 方法。
    [TestMethod]
    public void TestImm()
    {
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        var evenNumberQuery =
                        from number in numbers
                        where (number % 2) == 0
                        select number;
        int evenCount = evenNumberQuery.Count();
    }
    
    // 流式处理运算符不需要在生成元素前读取所有源数据。 在执行时，流式处理运算符一边读取每个源元素，一边对该源元素执行运算，并在可行时生成元素。
    // 非流式处理运算符必须先读取所有源数据，然后才能生成结果元素。 排序或分组等运算均属于此类别。
    /**
| 标准查询运算符         | 返回类型                                      | 立即执行 | 延迟的流式处理执行 | 延迟非流式处理执行 |
| ---------------------- | ------------------------------------------ | -------- | ----------------- | ----------------- |
| Aggregate              | `TSource`                                   | X        |                   |                   |
| All                    | Boolean                                     | X        |                   |                   |
| Any                    | Boolean                                     | X        |                   |                   |
| AsEnumerable           | IEnumerable<T>                              |          | X                 |                   |
| Average                | 单个数值                                     | X        |                   |                   |
| Cast                   | IEnumerable<T>                              |          | X                 |                   |
| Concat                 | IEnumerable<T>                              |          | X                 |                   |
| Contains               | Boolean                                     | X        |                   |                   |
| Count                  | Int32                                       | X        |                   |                   |
| DefaultIfEmpty         | IEnumerable<T>                              |          | X                 |                   |
| Distinct               | IEnumerable<T>                              |          | X                 |                   |
| ElementAt              | `TSource`                                   | X        |                   |                   |
| ElementAtOrDefault     | `TSource?`                                  | X        |                   |                   |
| Empty                  | IEnumerable<T>                              | X        |                   |                   |
| Except                 | IEnumerable<T>                              |          | X                 | X                 |
| First                  | `TSource`                                   | X        |                   |                   |
| FirstOrDefault         | `TSource?`                                  | X        |                   |                   |
| GroupBy                | IEnumerable<T>                              |          |                   | X                 |
| GroupJoin              | IEnumerable<T>                              |          | X                 | X                 |
| Intersect              | IEnumerable<T>                              |          | X                 | X                 |
| Join                   | IEnumerable<T>                              |          | X                 | X                 |
| Last                   | `TSource`                                    | X        |                   |                   |
| LastOrDefault          | `TSource?`                                   | X        |                   |                   |
| LongCount              | Int64                                        | X        |                   |                   |
| Max                    | 单个数值 `TSource` 或 `TResult?`              | X        |                   |                   |
| Min                    | 单个数值 `TSource` 或 `TResult?`              | X        |                   |                   |
| OfType                 | IEnumerable<T>                              |          | X                 |                   |
| OrderBy                | IOrderedEnumerable<TElement>                |          |                   | X                 |
| OrderByDescending      | IOrderedEnumerable<TElement>                |          |                   | X                 |
| Range                  | IEnumerable<T>                              |          | X                 |                   |
| Repeat                 | IEnumerable<T>                              |          | X                 |                   |
| Reverse                | IEnumerable<T>                              |          |                   | X                 |
| Select                 | IEnumerable<T>                              |          | X                 |                   |
| SelectMany             | IEnumerable<T>                              |          | X                 |                   |
| SequenceEqual          | Boolean                                     | X        |                   |                   |
| Single                 | `TSource`                                   | X        |                   |                   |
| SingleOrDefault        | `TSource?`                                  | X        |                   |                   |
| Skip                   | IEnumerable<T>                              |          | X                 |                   |
| SkipWhile              | IEnumerable<T>                              |          | X                 |                   |
| Sum                    | 单个数值                                     | X        |                   |                   |
| Take                   | IEnumerable<T>                              |          | X                 |                   |
| TakeWhile              | IEnumerable<T>                              |          | X                 |                   |
| ThenBy                 | IOrderedEnumerable<TElement>                |          |                   | X                 |
| ThenByDescending       | IOrderedEnumerable<TElement>                |          |                   | X                 |
| ToArray                | `TSource[]` 数组                             | X        |                   |                   |
| ToDictionary           | Dictionary<TKey,TValue>                     | X        |                   |                   |
| ToList                 | IList<T>                                    | X        |                   |                   |
| ToLookup               | ILookup<TKey,TElement>                      | X        |                   |                   |
| Union                  | IEnumerable<T>                              |          | X                 |                   |
| Where                  | IEnumerable<T>                              |          | X                 |                   |
     */
}