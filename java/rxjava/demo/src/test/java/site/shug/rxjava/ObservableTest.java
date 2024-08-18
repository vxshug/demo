package site.shug.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * RxJava 中的 Observable 是核心组件之一，它代表了一个可以发射一系列事件（数据或通知）的对象。这些事件可以是同步的或异步的，Observable 允许以一种响应式编程的方式处理数据流。
 *
 * Observable 是一种可观察的流，可以发射三种类型的事件：
 * onNext(T value): 发射一个数据项，订阅者（Observer）可以处理这个数据项。
 * onError(Throwable e): 发射一个错误事件，通知订阅者发生了错误，并终止流。
 * onComplete(): 发射完成事件，通知订阅者数据流结束。
 */

public class ObservableTest {
    /**
     * 通过{@code Observable.create()}自定义创建Observable
     */
    @Test
    void testObservableCreate() {
        Observable<Integer> ob1 = Observable.create(new ObservableOnSubscribe<>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(0);
                emitter.onComplete();
            }
        });
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull Integer o) {
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }
    /**
     * 通过{@code Observable.just()}根据just的参数创建一个Observable <br/>
     */
    @Test
    void testObservableJust() {
        Observable<Integer> ob1 = Observable.just(1, 2, 3);
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull Integer o) {
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }
    /**
     * 通过{@code Observable.range()}创建一个返回Integer的Observable <br/>
     * 通过{@code Observable.rangeLong()}创建一个返回Long的Observable <br/>
     * 通过{@code Observable.intervalRange()}创建一个返回Long的Observable <br/>
     */
    @Test
    void testObservableRange() {
        System.out.println("range");
        Observable<Integer> ob1 = Observable.range(0, 10);
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull Integer o) {
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
        System.out.println("rangeLong");
        Observable<Long> ob2 = Observable.rangeLong(0, 10);
        ob2.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull Long o) {
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });

        Observable<Long> ob3 = Observable.intervalRange(0, 10, 1, 20, TimeUnit.SECONDS);
        ob3.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull Long o) {
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }
    /**
     * 通过{@code Observable.never()}创建一个不返回的Observable, 不能检查到{@code Observable}完成
     */
    @Test
    void testObservableNever() {
        Observable<Object> ob1 = Observable.never();
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull Object o) {
                System.out.println("没有接收到值");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }
    /**
     * 通过{@code Observable.empty()}创建一个空的Observable不会返回任何值, 可以检查到{@code Observable}完成
     */
    @Test
    void testObservableEmpty() {
        Observable<Object> ob1 = Observable.empty();
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull Object o) {
                System.out.println("没有接收到值");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }
    /**
     * 通过{@code Observable.error()}创建一个Observable, 只能检查到错误
     */
    @Test
    void testObservableError() {
        Observable<Object> ob1 = Observable.error(new RuntimeException("Error"));
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull Object o) {
                System.out.println("没有接收到值");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }
    /**
     * Observable.fromAction()
     * 通过{@code Action}创建{@code Observable}, {@code Action}是不返回任何值的任务, {@code Observable}不会返回任何值, 可以检查到{@code Action}完成
     */
    @Test
    void testObservableFromAction() {
        Observable<Object> ob1 = Observable.fromAction(new Action() {
            @Override
            public void run() throws Throwable {
                System.out.println("Action");
            }
        });
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull Object o) {
                System.out.println("没有接收到值");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }

    /**
     * 将 {@code Future} 转换为 {@code Observable}<br/>
     *  {@code Observable.fromFuture(Future future)}调用{@code Future.get()}获取值<br/>
     *  {@code Observable.fromFuture(Future future, long timeout, TimeUnit unit)}调用{@code Future.get(long timeout, TimeUnit unit)}获取值
     */
    @Test
    void testObservableFromFuture() {
        Future<String> f1 = new Future<>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                System.out.println("Future cancel: mayInterruptIfRunning is " + mayInterruptIfRunning);
                return false;
            }

            @Override
            public boolean isCancelled() {
                System.out.println("Future isCancelled");
                return false;
            }

            @Override
            public boolean isDone() {
                System.out.println("Future isDone");
                return false;
            }

            @Override
            public String get() throws InterruptedException, ExecutionException {
                System.out.println("Future get");
                return "Future Value";
            }

            @Override
            public String get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                System.out.println("Future get with timeout " + timeout + " " + unit);
                return "Future Value: timeout is " + timeout + " unit: " + unit;
            }
        };
        Observable<String> ob1 = Observable.fromFuture(f1);
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull String o) {
                Assertions.assertEquals("Future Value", o);
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
        Observable<String> ob2 = Observable.fromFuture(f1, 1, TimeUnit.SECONDS);
        ob2.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull String o) {
                Assertions.assertEquals("Future Value: timeout is 1 unit: SECONDS", o);
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }

    /**
     * {@code Change access modifier} 转换为 {@code Observable}
     */
    @Test
    void testObservableFromArray() {
        Integer[] a1 = new Integer[]{1, 2, 3, 4, 5};
        Observable<Integer> ob1 = Observable.fromArray(a1);
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull Integer o) {
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }

    /**
     * 将 {@code Callable} 转换为 {@code Observable}
     */
    @Test
    void testObservableFromCallable() {
        Observable<String> ob1 = Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Callable";
            }
        });
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull String o) {
                Assertions.assertEquals("Callable", o);
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }

    /**
     * 将 {@code Completable} 转换为 {@code Observable}
     * 只能获取完成状态没有返回值
     */
    @Test
    void testObservableFromCompletable() {
        Observable<String> ob1 = Observable.fromCompletable(new Completable() {

            @Override
            protected void subscribeActual(@NonNull CompletableObserver observer) {
                System.out.println("subscribeActual start");
                Disposable disposable = new Disposable() {
                    @Override
                    public void dispose() {
                        System.out.println("dispose");
                    }

                    @Override
                    public boolean isDisposed() {
                        return false;
                    }
                };
                observer.onSubscribe(disposable);
                observer.onComplete();
                System.out.println("subscribeActual");
            }
        });
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull String o) {
                Assertions.assertEquals("Callable", o);
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }
    /**
     * 将 {@code CompletionStage} 转换为 {@code Observable}
     * {@code CompletionStage.whenComplete}可以返回一个数据或异常
     */
    @Test
    void testObservableFromCompletionStage() {
        Observable<String> ob1 = Observable.fromCompletionStage(new CompletionStage<>() {

            @Override
            public <U> CompletionStage<U> thenApply(Function<? super String, ? extends U> fn) {
                return null;
            }

            @Override
            public <U> CompletionStage<U> thenApplyAsync(Function<? super String, ? extends U> fn) {
                return null;
            }

            @Override
            public <U> CompletionStage<U> thenApplyAsync(Function<? super String, ? extends U> fn, Executor executor) {
                return null;
            }

            @Override
            public CompletionStage<Void> thenAccept(Consumer<? super String> action) {
                return null;
            }

            @Override
            public CompletionStage<Void> thenAcceptAsync(Consumer<? super String> action) {
                return null;
            }

            @Override
            public CompletionStage<Void> thenAcceptAsync(Consumer<? super String> action, Executor executor) {
                return null;
            }

            @Override
            public CompletionStage<Void> thenRun(Runnable action) {
                return null;
            }

            @Override
            public CompletionStage<Void> thenRunAsync(Runnable action) {
                return null;
            }

            @Override
            public CompletionStage<Void> thenRunAsync(Runnable action, Executor executor) {
                return null;
            }

            @Override
            public <U, V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super String, ? super U, ? extends V> fn) {
                return null;
            }

            @Override
            public <U, V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super String, ? super U, ? extends V> fn) {
                return null;
            }

            @Override
            public <U, V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super String, ? super U, ? extends V> fn, Executor executor) {
                return null;
            }

            @Override
            public <U> CompletionStage<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super String, ? super U> action) {
                return null;
            }

            @Override
            public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super String, ? super U> action) {
                return null;
            }

            @Override
            public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super String, ? super U> action, Executor executor) {
                return null;
            }

            @Override
            public CompletionStage<Void> runAfterBoth(CompletionStage<?> other, Runnable action) {
                return null;
            }

            @Override
            public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action) {
                return null;
            }

            @Override
            public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action, Executor executor) {
                return null;
            }

            @Override
            public <U> CompletionStage<U> applyToEither(CompletionStage<? extends String> other, Function<? super String, U> fn) {
                return null;
            }

            @Override
            public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends String> other, Function<? super String, U> fn) {
                return null;
            }

            @Override
            public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends String> other, Function<? super String, U> fn, Executor executor) {
                return null;
            }

            @Override
            public CompletionStage<Void> acceptEither(CompletionStage<? extends String> other, Consumer<? super String> action) {
                return null;
            }

            @Override
            public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends String> other, Consumer<? super String> action) {
                return null;
            }

            @Override
            public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends String> other, Consumer<? super String> action, Executor executor) {
                return null;
            }

            @Override
            public CompletionStage<Void> runAfterEither(CompletionStage<?> other, Runnable action) {
                return null;
            }

            @Override
            public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action) {
                return null;
            }

            @Override
            public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action, Executor executor) {
                return null;
            }

            @Override
            public <U> CompletionStage<U> thenCompose(Function<? super String, ? extends CompletionStage<U>> fn) {
                return null;
            }

            @Override
            public <U> CompletionStage<U> thenComposeAsync(Function<? super String, ? extends CompletionStage<U>> fn) {
                return null;
            }

            @Override
            public <U> CompletionStage<U> thenComposeAsync(Function<? super String, ? extends CompletionStage<U>> fn, Executor executor) {
                return null;
            }

            @Override
            public <U> CompletionStage<U> handle(BiFunction<? super String, Throwable, ? extends U> fn) {
                return null;
            }

            @Override
            public <U> CompletionStage<U> handleAsync(BiFunction<? super String, Throwable, ? extends U> fn) {
                return null;
            }

            @Override
            public <U> CompletionStage<U> handleAsync(BiFunction<? super String, Throwable, ? extends U> fn, Executor executor) {
                return null;
            }

            @Override
            public CompletionStage<String> whenComplete(BiConsumer<? super String, ? super Throwable> action) {
                System.out.println("whenComplete");
                action.accept("whenComplete", null);
                return null;
            }

            @Override
            public CompletionStage<String> whenCompleteAsync(BiConsumer<? super String, ? super Throwable> action) {
                return null;
            }

            @Override
            public CompletionStage<String> whenCompleteAsync(BiConsumer<? super String, ? super Throwable> action, Executor executor) {
                return null;
            }

            @Override
            public CompletionStage<String> exceptionally(Function<Throwable, ? extends String> fn) {
                return null;
            }

            @Override
            public CompletableFuture<String> toCompletableFuture() {
                System.out.println("toCompletableFuture");
                return new CompletableFuture<>();
            }
        });
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull String o) {
                Assertions.assertEquals("whenComplete", o);
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }
    /**
     * 将 {@code Iterable} 转换为 {@code Observable}
     */
    @Test
    void testObservableFromIterable() {
        Observable<String> ob1 = Observable.fromIterable(new Iterable<>() {

            @Override
            @NonNull
            public Iterator<String> iterator() {
                return new Iterator<>() {
                    private int index = 5;
                    @Override
                    public boolean hasNext() {
                        return index > 0;
                    }

                    @Override
                    public String next() {
                        index -= 1;
                        return "Iterable";
                    }
                };
            }
        });
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull String o) {
                Assertions.assertEquals("Iterable", o);
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }

    /**
     * 将 {@code MaybeSource} 转换为 {@code Observable}
     */
    @Test
    void testObservableFromMaybe() {
        Observable<String> ob1 = Observable.fromMaybe(new MaybeSource<>() {
            @Override
            public void subscribe(@NonNull MaybeObserver<? super String> observer) {
                Disposable disposable = new Disposable() {
                    @Override
                    public void dispose() {
                        System.out.println("dispose");
                    }

                    @Override
                    public boolean isDisposed() {
                        return false;
                    }
                };
                observer.onSubscribe(disposable);
                observer.onSuccess("MaybeObserver");
                observer.onComplete();
            }
        });
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull String o) {
                Assertions.assertEquals("MaybeObserver", o);
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }

    /**
     * 将 {@code Optional} 转换为 {@code Observable}
     */
    @Test
    void testObservableFromOptional() {
        Observable<String> ob1 = Observable.fromOptional(Optional.of("Optional"));
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull String o) {
                Assertions.assertEquals("Optional", o);
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }

    /**
     * 将 {@code Publisher} 转换为 {@code Observable}
     */
    @Test
    void testObservableFromPublisher() {
        Observable<String> ob1 = Observable.fromPublisher(new Publisher<String>() {
            @Override
            public void subscribe(Subscriber<? super String> subscriber) {
                subscriber.onSubscribe(new Subscription() {

                    @Override
                    public void request(long l) {
                        System.out.println("request: " + l);
                    }

                    @Override
                    public void cancel() {
                        System.out.println("cancel");
                    }
                });
                subscriber.onNext("Publisher");
                subscriber.onComplete();
            }
        });
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull String o) {
                Assertions.assertEquals("Publisher", o);
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }
    /**
     * 将 {@code Runnable} 转换为 {@code Observable}
     */
    @Test
    void testObservableFromRunnable() {
        Observable<String> ob1 = Observable.fromRunnable(new Runnable() {

            @Override
            public void run() {
                System.out.println("Runnable run");
            }
        });
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull String o) {
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }

    /**
     * 将 {@code SingleSource} 转换为 {@code Observable}
     */
    @Test
    void testObservableFromSingle() {
        Observable<String> ob1 = Observable.fromSingle(new SingleSource<>() {

            @Override
            public void subscribe(@NonNull SingleObserver<? super String> observer) {
                Disposable disposable = new Disposable() {
                    @Override
                    public void dispose() {
                        System.out.println("dispose");
                    }

                    @Override
                    public boolean isDisposed() {
                        return false;
                    }
                };
                observer.onSubscribe(disposable);
                observer.onSuccess("SingleSource");
            }
        });
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull String o) {
                Assertions.assertEquals("SingleSource", o);
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }

    /**
     * 将 {@code Stream} 转换为 {@code Observable}
     */
    @Test
    void testObservableFromStream() {
        Observable<String> ob1 = Observable.fromStream(new Stream<>() {
            @Override
            public Stream<String> filter(Predicate<? super String> predicate) {
                return Stream.empty();
            }

            @Override
            public <R> Stream<R> map(Function<? super String, ? extends R> mapper) {
                return Stream.empty();
            }

            @Override
            public IntStream mapToInt(ToIntFunction<? super String> mapper) {
                return IntStream.empty();
            }

            @Override
            public LongStream mapToLong(ToLongFunction<? super String> mapper) {
                return LongStream.empty();
            }

            @Override
            public DoubleStream mapToDouble(ToDoubleFunction<? super String> mapper) {
                return DoubleStream.empty();
            }

            @Override
            public <R> Stream<R> flatMap(Function<? super String, ? extends Stream<? extends R>> mapper) {
                return Stream.empty();
            }

            @Override
            public IntStream flatMapToInt(Function<? super String, ? extends IntStream> mapper) {
                return IntStream.empty();
            }

            @Override
            public LongStream flatMapToLong(Function<? super String, ? extends LongStream> mapper) {
                return LongStream.empty();
            }

            @Override
            public DoubleStream flatMapToDouble(Function<? super String, ? extends DoubleStream> mapper) {
                return DoubleStream.empty();
            }

            @Override
            public Stream<String> distinct() {
                return Stream.empty();
            }

            @Override
            public Stream<String> sorted() {
                return Stream.empty();
            }

            @Override
            public Stream<String> sorted(Comparator<? super String> comparator) {
                return Stream.empty();
            }

            @Override
            public Stream<String> peek(Consumer<? super String> action) {
                return Stream.empty();
            }

            @Override
            public Stream<String> limit(long maxSize) {
                return Stream.empty();
            }

            @Override
            public Stream<String> skip(long n) {
                return Stream.empty();
            }

            @Override
            public void forEach(Consumer<? super String> action) {

            }

            @Override
            public void forEachOrdered(Consumer<? super String> action) {

            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <A> A[] toArray(IntFunction<A[]> generator) {
                return null;
            }

            @Override
            public String reduce(String identity, BinaryOperator<String> accumulator) {
                return "";
            }

            @Override
            public Optional<String> reduce(BinaryOperator<String> accumulator) {
                return Optional.empty();
            }

            @Override
            public <U> U reduce(U identity, BiFunction<U, ? super String, U> accumulator, BinaryOperator<U> combiner) {
                return null;
            }

            @Override
            public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super String> accumulator, BiConsumer<R, R> combiner) {
                return null;
            }

            @Override
            public <R, A> R collect(Collector<? super String, A, R> collector) {
                return null;
            }

            @Override
            public Optional<String> min(Comparator<? super String> comparator) {
                return Optional.empty();
            }

            @Override
            public Optional<String> max(Comparator<? super String> comparator) {
                return Optional.empty();
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public boolean anyMatch(Predicate<? super String> predicate) {
                return false;
            }

            @Override
            public boolean allMatch(Predicate<? super String> predicate) {
                return false;
            }

            @Override
            public boolean noneMatch(Predicate<? super String> predicate) {
                return false;
            }

            @Override
            public Optional<String> findFirst() {
                return Optional.empty();
            }

            @Override
            public Optional<String> findAny() {
                return Optional.empty();
            }

            @Override
            @NonNull
            public Iterator<String> iterator() {
                return new Iterator<>() {
                    private int index = 5;
                    @Override
                    public boolean hasNext() {
                        return index > 0;
                    }

                    @Override
                    public String next() {
                        index -= 1;
                        return "Stream";
                    }
                };
            }

            @Override
            public Spliterator<String> spliterator() {
                return null;
            }

            @Override
            public boolean isParallel() {
                return false;
            }

            @Override
            public Stream<String> sequential() {
                return Stream.empty();
            }

            @Override
            public Stream<String> parallel() {
                return Stream.empty();
            }

            @Override
            public Stream<String> unordered() {
                return Stream.empty();
            }

            @Override
            public Stream<String> onClose(Runnable closeHandler) {
                return Stream.empty();
            }

            @Override
            public void close() {

            }
        });
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull String o) {
                Assertions.assertEquals("Stream", o);
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }
    /**
     * 将 {@code Supplier} 转换为 {@code Observable}
     */
    @Test
    void testObservableFromSupplier() {
        Observable<String> ob1 = Observable.fromSupplier(new io.reactivex.rxjava3.functions.Supplier<>() {

            @Override
            public String get() throws Throwable {
                return "Supplier";
            }
        });
        ob1.subscribe(new Observer<>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("开启订阅");
            }

            @Override
            public void onNext(@NonNull String o) {
                Assertions.assertEquals("Supplier", o);
                System.out.println("接收到值: " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("发生错误");
            }

            @Override
            public void onComplete() {
                System.out.println("任务完成");
            }
        });
    }
}
