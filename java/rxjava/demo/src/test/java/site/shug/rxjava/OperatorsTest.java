package site.shug.rxjava;

import io.reactivex.rxjava3.core.Flowable;
import org.junit.jupiter.api.Test;

/**
 * 操作符是 RxJava 的强大特性，它们允许你在数据流的发射和订阅之间进行转换、过滤、组合和处理数据。
 */
public class OperatorsTest {
    // 将每个发射的数据项一对一转换。
    @Test
    void testMap() {
        Flowable<Integer> flowable = Flowable.just(1, 2, 3, 4, 5);
        flowable.map(integer -> integer * 2)
                .subscribe(System.out::println);
    }
    // 过滤掉不符合条件的数据项。
    @Test
    void testFilter() {
        Flowable<Integer> flowable = Flowable.just(1, 2, 3, 4, 5);
        flowable.filter(integer -> integer > 2)
                .subscribe(System.out::println);
    }
    // 将一个 Observable 发射的数据项转换为多个 Observable，然后合并它们。
    @Test
    void testFlatMap() {
        Flowable<Integer> flowable = Flowable.just(1, 2, 3, 4, 5);
        flowable
                .flatMap(integer -> Flowable.just(integer, integer * 2))
                .subscribe(System.out::println);
    }
    // 通过一个函数，将所有数据项累计为一个值。
    @Test
    void testReduce() {
        Flowable<Integer> flowable = Flowable.just(1, 2, 3, 4, 5);
        flowable
                .reduce((pre, cur) -> pre + cur)
                .subscribe(System.out::println);
    }
    // 合并多个 Observable，将它们的发射物交错输出。
    @Test
    void testMergeWith() {
        Flowable<Integer> f1 = Flowable.just(1, 2, 3, 4, 5);
        Flowable<Integer> f2 = Flowable.just(6, 7, 8);
        f1
                .mergeWith(f2)
                .subscribe(System.out::println);
    }
    // 顺序地连接多个 Observable，逐个发射它们的数据项。
    @Test
    void testConcat() {
        Flowable<Integer> f1 = Flowable.just(1, 2, 3, 4, 5);
        Flowable<Integer> f2 = Flowable.just(6, 7, 8);
        f1
                .concatWith(f2)
                .subscribe(System.out::println);
    }
    //  将多个 Observable 的数据项按照顺序结合成新的数据项。
    @Test
    void testZip() {
        Flowable<Integer> f1 = Flowable.just(1, 2, 3, 4, 5);
        Flowable<Integer> f2 = Flowable.just(6, 7, 8);
        f1
                .zipWith(f2, (r1, r2) -> r1 + r2)
                .subscribe(System.out::println);
    }
}
