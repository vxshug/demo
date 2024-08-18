package site.shug.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * 背压: 在异步数据流处理中，背压是指数据生产者产生数据的速度快于消费者处理数据的速度，从而导致数据积压。如果这种积压不能得到有效管理，就可能导致系统性能下降甚至崩溃。
 * Flowable 是 RxJava 中的一种特殊类型的 Observable，
 * 专门用于处理背压（Backpressure）问题。当数据生产速度超过数据消费速度时，
 * 背压问题会导致缓冲区溢出或内存耗尽。
 * 在这种情况下，Flowable 提供了更好的控制和管理手段。
 * Flowable订阅时必须指定请求的数量, 不然会没有数据
 */
public class FlowableTest {

    /**
     * {@code Flowable.create}创建Flowable, 可以指定背压策略<br/>
     * {@code BackpressureStrategy.BUFFER}: 使用无限制的缓冲区存储发射的项，直到它们被消费者处理。如果生产速度过快，这可能会导致内存占用过多。<br/>
     * {@code BackpressureStrategy.DROP}: 丢弃无法及时处理的数据项，只保留最近的数据项。<br/>
     * {@code BackpressureStrategy.LATEST}: 丢弃所有旧的数据项，只保留最新的那一项数据。<br/>
     * {@code BackpressureStrategy.MISSING}: 不提供默认背压策略，你需要手动处理背压。<br/>
     * {@code BackpressureStrategy.ERROR}: 如果不能及时处理数据项，则发射 MissingBackpressureException 错误。<br/>
     */
    @Test
    void flowableCreate() {
        Flowable<Integer> f1 = Flowable.create(new FlowableOnSubscribe<>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Integer> emitter) throws Throwable {
                for (int i = 0; i < 10; i++) {
                    if (emitter.isCancelled()) {
                        return;
                    }
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        f1.subscribe(new Subscriber<>() {

            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("onSubscribe");
                subscription.request(10);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext: " + integer);

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}
