package site.shug.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * 调度器决定了 Observable 和 Observer 在哪个线程上工作。
 * RxJava 提供了多种调度器来处理不同类型的任务，比如 I/O 操作、计算密集型任务和主线程操作。
 * <p>
 * 常用的调度器包括：</br>
 * Schedulers.io(): 用于 I/O 操作（文件、网络请求等），线程池可以重用。</br>
 * Schedulers.computation(): 用于计算密集型任务，如事件循环或处理回调。</br>
 * Schedulers.newThread(): 每次调用都会启动一个新线程。</br>
 * Schedulers.single(): 在一个单一的线程上执行。</br>
 * AndroidSchedulers.mainThread(): Android 特有的，用于在主线程上操作 UI。</br>
 * </p>
 */
public class SchedulerTest {
    void printThreadName(String prefix) {
        System.out.println(prefix + ": " + Thread.currentThread().getName());
    }
    // subscribeOn指定 Observable 从订阅开始的执行线程或调度器。它影响的是从源头开始的整个流的执行线程。
    @Test
    void schedulerThreadName() {
        printThreadName("schedulerThreadName");
        Observable<Integer> ob1 = Observable.create(new ObservableOnSubscribe<>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                printThreadName("Observable");
                emitter.onNext(1);
                emitter.onComplete();
            }
        });
        ob1
                .subscribe(v -> printThreadName("no"));
        ob1.subscribeOn(Schedulers.io())
                .map(integer -> {
                    printThreadName("io");
                    return integer + 1;
                })
                .subscribe(v -> printThreadName("io"));
        ob1.subscribeOn(Schedulers.computation())
                .subscribe(v -> printThreadName("computation"));
        ob1.subscribeOn(Schedulers.newThread())
                .subscribe(v -> printThreadName("newThread"));
        ob1.subscribeOn(Schedulers.single())
                .subscribe(v -> printThreadName("single"));
    }

    // observeOn修改在调用该操作符之后的执行线程或调度器。它仅影响该点之后的操作符执行线程。
    @Test
    void observeOnSchedulerThreadName() {
        printThreadName("schedulerThreadName");
        Observable<Integer> ob1 = Observable.create(new ObservableOnSubscribe<>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                printThreadName("Observable");
                emitter.onNext(1);
                emitter.onComplete();
            }
        });
        ob1
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(integer -> {
                    printThreadName("Observer");
                    return integer + 1;
                })
                .subscribe(v -> printThreadName("Observer"));
    }
    // custom Scheduler
    @Test
    void customScheduler() {
        printThreadName("schedulerThreadName");
        Observable<Integer> ob1 = Observable.just(1);
        ob1
                .observeOn(new Scheduler() {
                    @Override
                    public @NonNull Worker createWorker() {
                        return new Worker() {

                            @Override
                            public void dispose() {
                                System.out.println("Worker.dispose");
                            }

                            @Override
                            public boolean isDisposed() {
                                System.out.println("Worker.isDisposed");
                                return false;
                            }

                            @Override
                            public @NonNull Disposable schedule(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                                //  指定运行线程
                                Thread thread = new Thread(run, "customThread");
                                thread.start();
                                return new Disposable() {
                                    @Override
                                    public void dispose() {
                                        System.out.println("Disposable.dispose");
                                    }

                                    @Override
                                    public boolean isDisposed() {
                                        System.out.println("Disposable.isDisposed");
                                        return false;
                                    }
                                };
                            }
                        };
                    }
                })
                .subscribe(v -> printThreadName("Observer"));
    }

}
