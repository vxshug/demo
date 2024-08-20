package site.shug.spring.lifecycle;

import org.springframework.context.Lifecycle;

public class LifecycleLife implements Lifecycle {
    @Override
    public void start() {
        System.out.println("LifecycleLife: start");
    }

    @Override
    public void stop() {
        System.out.println("LifecycleLife: stop");

    }

    @Override
    public boolean isRunning() {
        System.out.println("LifecycleLife: isRunning");
        return false;
    }
}
