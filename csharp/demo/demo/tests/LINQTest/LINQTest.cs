namespace Demo.tests.LINQTest;

[TestClass]
public class LINQTest
{
    // 多个from
    [TestMethod]
    public void TestSelect()
    {
        var list = new List<string> { "A", "B", "C" };
        var numbers = new List<int> { 1, 2, 3, 4 };
        var ret = from n in numbers
                        from a in list 
                        where n > 1
                        select a;
        var r = ret.ToList();
        // 返回3次所有list的元素
        CollectionAssert.AreEqual(new List<string>(){"A", "B", "C", "A", "B", "C", "A", "B", "C"}, r);
    }

    // group语句
    [TestMethod]
    public void TestGroupBy()
    {
        var list = new List<string> { "AC", "AB", "CD", "DA", "E" };
        var groupQuery =
                        from a in list
                        group a by a[0];
        var groupList = new List<string>();
        // 取第一个分组
        foreach (var ig in groupQuery)
        {
            foreach (var s in ig)
            {
                groupList.Add(s);
            }
            break;
        }
        CollectionAssert.AreEqual(new List<string>(){"AC", "AB"}, groupList);
    }

    // 排序
    [TestMethod]
    public void TestOrder()
    {
        int[] numbers = { 1, 2, 3, 4, 7, 6, 5 };
        var numbersQuery =
                        from number in numbers 
                        orderby number descending
                        select number;
        CollectionAssert.AreEqual(new List<int>(){7,6,5,4,3,2,1},numbersQuery.ToList());
    }

    // into用于group或select后面, 对数据再处理
    [TestMethod]
    public void TestInto()
    {
        int[] numbers = { 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        var query =
                        from number in numbers
                        group number by number
                        into numberGroup
                        where numberGroup.Count() > 1
                        select numberGroup;
        CollectionAssert.AreEqual(new List<int>(){1, 1}, query.ToList().First().ToList());
    }

    /**
     * join
     */
    [TestMethod]
    public void TestJoin()
    {
        var table1 = new List<(int, int)> { (1, 2), (3, 4), (5, 6), (7, 8) };
        var table2 = new List<int>() { 1, 2, 3 };
        var joinQuery =
                        from t1 in table1
                        join t2 in table2 on t1.Item1 equals t2 
                        select new { t1, t2 };
        joinQuery.ToList().ForEach(Console.WriteLine);
    }

    /**
     * let定义变量
     */
    [TestMethod]
    public void TestLet()
    {
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        var letQuery =
                        from number in numbers 
                        let doubleValue = number * 2
                                        where doubleValue > 5
                                        select doubleValue;
        CollectionAssert.AreEqual(new List<int>(){ 6,8,10,12,14,16,18,20 }, letQuery.ToList());
    }
}