package site.shug.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import org.junit.jupiter.api.Test;

/**
 * Completable 是 RxJava 中的一种特殊类型的 Observable，与 Observable 和 Single 不同，Completable 既不发射数据项，也不发射成功的值。
 * 它只表示一个操作要么成功完成，要么失败并抛出错误。Completable 通常用于执行没有返回值的任务，如写入数据库、清理缓存、发送网络请求等。
 */
public class CompletableTest {
    @Test
    void testCompletable() {
        Completable completable = Completable.complete();
        completable.subscribe(new CompletableObserver() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError: " + e.getMessage());
            }
        });
    }

    @Test
    void testError() {
        Completable completable = Completable.error(new RuntimeException("Error"));
        completable.subscribe(new CompletableObserver() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError: " + e.getMessage());
            }
        });
    }
}
