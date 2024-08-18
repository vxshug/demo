package site.shug.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import org.junit.jupiter.api.Test;

/**
 * Single 是 RxJava 中的一种特殊类型的 Observable，它只会发射单个数据项或一个错误事件。Single 非常适合用于那些只需要处理单一结果的异步操作，比如网络请求、数据库查询等。</br>
 * Single 只会有两种可能的终止状态：</br>
 * onSuccess(T value): 发射一个单一的数据项并结束。</br>
 * onError(Throwable e): 发射一个错误事件并结束。</br>
 * Single 不会发射 onNext() 和 onComplete() 事件，而是直接发射 onSuccess() 或 onError()。</br>
 */
public class SingleTest {
    @Test
    void testSingleSuccess() {
        Single<String> single = Single.just("Success");

        single.subscribe(new SingleObserver<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onSuccess(@NonNull String s) {
                System.out.println("onSuccess: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }
        });
    }

    @Test
    void testSingleError() {
        Single<String> single = Single.error(new Throwable("Error"));

        single.subscribe(new SingleObserver<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onSuccess(@NonNull String s) {
                System.out.println("onSuccess: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }
        });
    }
}
