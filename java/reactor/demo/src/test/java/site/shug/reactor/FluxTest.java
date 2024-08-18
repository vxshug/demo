package site.shug.reactor;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.util.annotation.NonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.*;

/**
 * Flux 是 Reactor 库中的一个核心类，代表一个包含 0 到 N 个元素的异步序列。它是一个响应式流，可以用于处理数据流、事件流等。<br/>
 * Flux 的生命周期包括以下几个阶段：</br>
 *  订阅 (Subscription)：当一个订阅者 (Subscriber) 订阅了 Flux，订阅关系就建立了，此时 Flux 开始向订阅者发送数据。<br/>
 *  数据发布 (OnNext)：Flux 会通过 onNext 信号发送一个或多个数据项给订阅者。<br/>
 *  完成 (OnComplete)：当所有数据项都发送完毕后，Flux 会发送一个 onComplete 信号，表示数据流结束。<br/>
 *  错误 (OnError)：如果在数据发布过程中发生错误，Flux 会通过 onError 信号通知订阅者，并终止数据流。<br/>
 *
 * Flux默认支持背压机制，订阅者订阅消息时需要请求消息数量</br>
 * 在{@code Subscriber.onSubscribe(Subscription s)}中的{@code Subscription.request(Long.MAX_VALUE)}
 */
public class FluxTest {

    /**
     * {@code Flux.empty()} 创建一个发送完成状态的Flux
     */
    @Test
    void testEmpty() {
        Flux.empty()
                .subscribe(new Subscriber<>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext");
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }

    /**
     * {@code Flux.never()} 创建一个不发送状态的Flux
     */
    @Test
    void testNever() {
        Flux.never()
                .subscribe(new Subscriber<Object>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext");
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }

    /**
     * {@code Flux.error()} 创建一个发送错误状态的Flux
     */
    @Test
    void testError() {
        Flux.error(new RuntimeException("Error"))
                .subscribe(new Subscriber<Object>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext");
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }

    /**
     * {@code Flux.create()} 创建一个发送错误状态的Flux
     */
    @Test
    void testCreate() {
        Flux.create(new Consumer<FluxSink<Integer>>() {
                    @Override
                    public void accept(FluxSink<Integer> integerFluxSink) {
                        integerFluxSink.next(1);
                        integerFluxSink.complete();
                    }
                })
                .subscribe(new Subscriber<Object>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext");
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }

    /**
     * {@code Flux.just()} 根据just的参数创建Flux
     */
    @Test
    void testJust() {
        Flux.just("1", "2", "3")
                .subscribe(new Subscriber<Object>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext: " + o);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }

    /**
     * {@code Flux.range()} 根据range的开始和数量参数创建Flux
     */
    @Test
    void testRange() {
        Flux.range(0, 4)
                .subscribe(new Subscriber<>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(Integer o) {
                        System.out.println("onNext: " + o);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }

    /**
     * {@code Flux.from()} 根据Publisher创建Flux
     */
    @Test
    void testFrom() {
        Flux.from(new Publisher<Integer>() {
                    @Override
                    public void subscribe(Subscriber<? super Integer> s) {
                        s.onSubscribe(new Subscription() {
                            @Override
                            public void request(long n) {
                                System.out.println("request: " + n);
                            }
                            @Override
                            public void cancel() {
                                System.out.println("cancel");
                            }
                        });
                        s.onNext(0);
                        s.onComplete();
                    }
                })
                .subscribe(new Subscriber<>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(Integer o) {
                        System.out.println("onNext: " + o);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }

    /**
     * {@code Flux.fromArray()} 根据Array创建Flux
     */
    @Test
    void testFromArray() {
        Flux.fromArray(new Integer[]{1, 2, 3})
                .subscribe(new Subscriber<>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(Integer o) {
                        System.out.println("onNext: " + o);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }

    /**
     * {@code Flux.fromIterable()} 根据Iterable创建Flux
     */
    @Test
    void testFromIterable() {
        Flux.fromIterable(new Iterable<Integer>() {
                    @Override
                    public Iterator<Integer> iterator() {
                        return new Iterator<Integer>() {
                            private int i = 5;
                            @Override
                            public boolean hasNext() {
                                return i > 0;
                            }

                            @Override
                            public Integer next() {
                                i -= 1;
                                return i;
                            }
                        };
                    }
                })
                .subscribe(new Subscriber<>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(Integer o) {
                        System.out.println("onNext: " + o);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }

    /**
     * {@code Flux.fromStream()} 根据Stream创建Flux
     */
    @Test
    void testFromStream() {
        Flux.fromStream(new Stream<Integer>() {
                    private int index = 5;
                    @Override
                    public Iterator<Integer> iterator() {
                        return null;
                    }

                    @Override
                    @NonNull
                    public Spliterator<Integer> spliterator() {
                        index -= 1;
                        return new Spliterator<>() {
                            private int i = index;
                            @Override
                            public boolean tryAdvance(Consumer<? super Integer> action) {
                                i -= 1;
                                if (i > 0) {
                                    action.accept(i);
                                }

                                return false;
                            }

                            @Override
                            public Spliterator<Integer> trySplit() {
                                return null;
                            }

                            @Override
                            public long estimateSize() {
                                return i;
                            }

                            @Override
                            public int characteristics() {
                                return 0;
                            }
                        };
                    }

                    @Override
                    public boolean isParallel() {
                        return false;
                    }

                    @Override
                    public Stream<Integer> sequential() {
                        return Stream.empty();
                    }

                    @Override
                    public Stream<Integer> parallel() {
                        return Stream.empty();
                    }

                    @Override
                    public Stream<Integer> unordered() {
                        return Stream.empty();
                    }

                    @Override
                    public Stream<Integer> onClose(Runnable closeHandler) {
                        return Stream.empty();
                    }

                    @Override
                    public void close() {

                    }

                    @Override
                    public Stream<Integer> filter(Predicate<? super Integer> predicate) {
                        return Stream.empty();
                    }

                    @Override
                    public <R> Stream<R> map(Function<? super Integer, ? extends R> mapper) {
                        return Stream.empty();
                    }

                    @Override
                    public IntStream mapToInt(ToIntFunction<? super Integer> mapper) {
                        return IntStream.empty();
                    }

                    @Override
                    public LongStream mapToLong(ToLongFunction<? super Integer> mapper) {
                        return LongStream.empty();
                    }

                    @Override
                    public DoubleStream mapToDouble(ToDoubleFunction<? super Integer> mapper) {
                        return DoubleStream.empty();
                    }

                    @Override
                    public <R> Stream<R> flatMap(Function<? super Integer, ? extends Stream<? extends R>> mapper) {
                        return Stream.empty();
                    }

                    @Override
                    public IntStream flatMapToInt(Function<? super Integer, ? extends IntStream> mapper) {
                        return IntStream.empty();
                    }

                    @Override
                    public LongStream flatMapToLong(Function<? super Integer, ? extends LongStream> mapper) {
                        return LongStream.empty();
                    }

                    @Override
                    public DoubleStream flatMapToDouble(Function<? super Integer, ? extends DoubleStream> mapper) {
                        return DoubleStream.empty();
                    }

                    @Override
                    public Stream<Integer> distinct() {
                        return Stream.empty();
                    }

                    @Override
                    public Stream<Integer> sorted() {
                        return Stream.empty();
                    }

                    @Override
                    public Stream<Integer> sorted(Comparator<? super Integer> comparator) {
                        return Stream.empty();
                    }

                    @Override
                    public Stream<Integer> peek(Consumer<? super Integer> action) {
                        return Stream.empty();
                    }

                    @Override
                    public Stream<Integer> limit(long maxSize) {
                        return Stream.empty();
                    }

                    @Override
                    public Stream<Integer> skip(long n) {
                        return Stream.empty();
                    }

                    @Override
                    public void forEach(Consumer<? super Integer> action) {

                    }

                    @Override
                    public void forEachOrdered(Consumer<? super Integer> action) {

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
                    public Integer reduce(Integer identity, BinaryOperator<Integer> accumulator) {
                        return 0;
                    }

                    @Override
                    public Optional<Integer> reduce(BinaryOperator<Integer> accumulator) {
                        return Optional.empty();
                    }

                    @Override
                    public <U> U reduce(U identity, BiFunction<U, ? super Integer, U> accumulator, BinaryOperator<U> combiner) {
                        return null;
                    }

                    @Override
                    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super Integer> accumulator, BiConsumer<R, R> combiner) {
                        return null;
                    }

                    @Override
                    public <R, A> R collect(Collector<? super Integer, A, R> collector) {
                        return null;
                    }

                    @Override
                    public Optional<Integer> min(Comparator<? super Integer> comparator) {
                        return Optional.empty();
                    }

                    @Override
                    public Optional<Integer> max(Comparator<? super Integer> comparator) {
                        return Optional.empty();
                    }

                    @Override
                    public long count() {
                        return 0;
                    }

                    @Override
                    public boolean anyMatch(Predicate<? super Integer> predicate) {
                        return false;
                    }

                    @Override
                    public boolean allMatch(Predicate<? super Integer> predicate) {
                        return false;
                    }

                    @Override
                    public boolean noneMatch(Predicate<? super Integer> predicate) {
                        return false;
                    }

                    @Override
                    public Optional<Integer> findFirst() {
                        return Optional.empty();
                    }

                    @Override
                    public Optional<Integer> findAny() {
                        return Optional.empty();
                    }
                })
                .subscribe(new Subscriber<>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(Integer o) {
                        System.out.println("onNext: " + o);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }
}
