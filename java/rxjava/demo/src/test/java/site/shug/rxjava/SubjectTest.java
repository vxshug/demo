package site.shug.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import org.junit.jupiter.api.Test;

/**
 * Subject 是一种特殊的 Observable，同时也是 Observer。这意味着它可以订阅其他 Observable，同时也可以发射数据给自己的订阅者。
 */
public class SubjectTest {
    // 只会将来自 Observable 的数据发射给在订阅之后的数据项。
    @Test
    void testPublishSubject() {
        // 创建 PublishSubject
        PublishSubject<String> subject = PublishSubject.create();

        // 订阅者 订阅 Subject
        subject.subscribe(
                item -> System.out.println("Subscriber 1 received: " + item),
                throwable -> System.err.println("Error: " + throwable),
                () -> System.out.println("Subscriber 1 completed!")
        );

        // 发射几个事件
        subject.onNext("Hello");
        subject.onNext("RxJava");

        // 完成 Subject
        subject.onComplete();
    }

    // 发射最近的数据项（或初始值），然后继续发射后续数据。
    @Test
    void testBehaviorSubject() {
        BehaviorSubject<String> subject = BehaviorSubject.create();
        subject.onNext("Hello");
        // 订阅者 订阅 Subject
        subject.subscribe(
                item -> System.out.println("Subscriber 1 received: " + item),
                throwable -> System.err.println("Error: " + throwable),
                () -> System.out.println("Subscriber 1 completed!")
        );
        subject.onNext("RxJava");
        subject.onComplete();
    }

    // 不仅发射给定时间点之后的数据项，也会发射时间点之前的数据项，所有的订阅者都会收到所有数据项。
    @Test
    void testReplaySubject() {
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.onNext("Hello");
        // 订阅者 订阅 Subject
        subject.subscribe(
                item -> System.out.println("Subscriber 1 received: " + item),
                throwable -> System.err.println("Error: " + throwable),
                () -> System.out.println("Subscriber 1 completed!")
        );
        subject.onNext("RxJava");
        subject.onComplete();
    }

    // 只发射 Observable 完成时发射的最后一个数据项。
    @Test
    void testAsyncSubject() {
        AsyncSubject<String> subject = AsyncSubject.create();
        // 订阅者 订阅 Subject
        subject.subscribe(
                item -> System.out.println("Subscriber 1 received: " + item),
                throwable -> System.err.println("Error: " + throwable),
                () -> System.out.println("Subscriber 1 completed!")
        );
        subject.onNext("Hello");
        subject.onNext("RxJava");
        subject.onComplete();
    }
}
