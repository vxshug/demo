package site.shug.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import org.junit.jupiter.api.Test;

/**
 * Observer 是用于接收 Observable 发射的数据项的组件。Observer 订阅 Observable，并对发射的每一个事件做出响应。
 */
public class ObserverTest {
    @Test
    void testObserver() {
        Observable<String> ob1 = Observable.just("1", "2", "3");
        ob1.subscribe(new Observer<>() {
            // 订阅Observable时调用
            @Override
            public void onSubscribe(@NonNull Disposable d) {
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
