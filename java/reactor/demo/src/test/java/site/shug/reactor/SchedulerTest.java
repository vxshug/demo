package site.shug.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.annotation.NonNull;

import java.util.function.Consumer;

/**
 * 调度器决定了 Observable 和 Observer 在哪个线程上工作。
 * RxJava 提供了多种调度器来处理不同类型的任务，比如 I/O 操作、计算密集型任务和主线程操作。
 * <p>
 * 常用的调度器包括：</br>
 * Schedulers.immediate(): 在当前线程执行。</br>
 * Schedulers.single(): 在单一后台线程执行。。</br>
 * Schedulers.parallel(): 在多个后台线程上并行执行。</br>
 * Schedulers.boundedElastic(): 专为阻塞任务设计的弹性线程池。</br>
 * </p>
 */
public class SchedulerTest {
    void printThreadName(String prefix) {
        System.out.println(prefix + ": " + Thread.currentThread().getName());
    }
    // subscribeOn指定 Flux 从订阅开始的执行线程或调度器。它影响的是从源头开始的整个流的执行线程。
    @Test
    void schedulerThreadName() {
        printThreadName("schedulerThreadName");
        Flux<Integer> ob1 = Flux.create(new Consumer<FluxSink<Integer>>() {
            @Override
            public void accept(FluxSink<Integer> integerFluxSink) {
                integerFluxSink.next(1);
                integerFluxSink.complete();
            }
        });
        ob1
                .subscribe(v -> printThreadName("no"));
        ob1.subscribeOn(Schedulers.immediate())
                .subscribe(v -> printThreadName("immediate"));
        ob1.subscribeOn(Schedulers.single())
                .subscribe(v -> printThreadName("single"));
        ob1.subscribeOn(Schedulers.parallel())
                .subscribe(v -> printThreadName("parallel"));
        ob1.subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> printThreadName("boundedElastic"));
    }

    // publishOn指定 Flux 在调用该操作符之后的执行线程或调度器。它仅影响该点之后的操作符执行线程。
    @Test
    void observeOnSchedulerThreadName() {
        printThreadName("schedulerThreadName");
        Flux<Integer> ob1 = Flux.create(new Consumer<FluxSink<Integer>>() {
            @Override
            public void accept(FluxSink<Integer> integerFluxSink) {
                integerFluxSink.next(1);
                integerFluxSink.complete();
            }
        });
        ob1
                .subscribeOn(Schedulers.single())
                .publishOn(Schedulers.parallel())
                .subscribe(v -> printThreadName("subscribe"));
    }
    // custom Scheduler
    @Test
    void customScheduler() {
        printThreadName("schedulerThreadName");
        Flux<Integer> ob1 = Flux.just(1);
        ob1
                .publishOn(new Scheduler() {

                    @Override
                    public Disposable schedule(Runnable task) {
                        System.out.println("Scheduler.schedule");
                        Thread thread = new Thread(task, "Scheduler");
                        thread.start();
                        return new Disposable() {
                            @Override
                            public void dispose() {
                                System.out.println("Scheduler.Disposable.dispose");
                            }
                        };
                    }

                    @Override
                    public Worker createWorker() {
                        return new Worker() {
                            @Override
                            public Disposable schedule(Runnable task) {
                                System.out.println("Worker.schedule");
                                Thread thread = new Thread(task, "Worker");
                                thread.start();
                                return new Disposable() {

                                    @Override
                                    public void dispose() {
                                        System.out.println("Worker.Disposable.dispose");
                                    }
                                };
                            }

                            @Override
                            public void dispose() {
                                System.out.println("Worker.dispose");
                            }
                        };
                    }
                })
                .subscribe(v -> printThreadName("subscribe"));
    }
}
