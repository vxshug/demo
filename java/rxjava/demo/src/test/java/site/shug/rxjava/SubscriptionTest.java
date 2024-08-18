package site.shug.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import org.junit.jupiter.api.Test;

/**
 * Subscription(订阅)是 Observer 和 Observable 之间的连接。通过订阅，Observer 开始接收 Observable 发射的数据。</br>
 * 订阅可以通过 Disposable 对象来管理，用于在不再需要接收数据时取消订阅，以避免内存泄漏。
 */
public class SubscriptionTest {
    @Test
    void testSubscribe() {
        Observable<String> ob1 = Observable.just("1", "2", "3");
        ob1.subscribe(new Observer<>() {
            // 订阅Observable时调用
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                //  取消订阅
                d.dispose();
                System.out.println("onSubscribe");
            }

            // 当 Observable 发射一个数据项时调用。
            @Override
            public void onNext(@NonNull String s) {
                System.out.println("onNext: " + s);
            }

            // 当 Observable 发射一个错误事件时调用。
            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError " + e.getMessage());
            }

            // 当 Observable 发射完成事件时调用，表示数据流结束。
            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}
