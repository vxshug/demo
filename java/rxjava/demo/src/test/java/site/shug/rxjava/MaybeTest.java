package site.shug.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import org.junit.jupiter.api.Test;

/**
 * Maybe 是 RxJava 中的一种特殊类型的 Observable，它介于 Single 和 Completable 之间。</br>
 * Maybe 可以发射一个单一的值、没有值，或者一个错误事件。Maybe 非常适合处理可能有值、也可能没有值的异步操作，如从数据库中查找某个条目。</br>
 *
 * onSuccess(T value): 发射一个单一的数据项并结束。</br>
 * onComplete(): 没有发射任何数据项，但操作成功完成。</br>
 * onError(Throwable e): 发射一个错误事件并结束。<br/>
 */
public class MaybeTest {
    @Test
    void testSuccess() {
        Maybe<Integer> maybe = Maybe.just(1);
        maybe.subscribe(new MaybeObserver<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onSuccess(@NonNull Integer integer) {
                System.out.println("onSuccess: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    @Test
    void testComplete() {
        Maybe<Integer> maybe = Maybe.empty();
        maybe.subscribe(new MaybeObserver<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onSuccess(@NonNull Integer integer) {
                System.out.println("onSuccess: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    @Test
    void testError() {
        Maybe<Integer> maybe = Maybe.error(new RuntimeException("Error"));
        maybe.subscribe(new MaybeObserver<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onSuccess(@NonNull Integer integer) {
                System.out.println("onSuccess: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}
