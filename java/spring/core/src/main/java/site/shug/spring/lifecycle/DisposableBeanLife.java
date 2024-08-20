package site.shug.spring.lifecycle;

import org.springframework.beans.factory.DisposableBean;

public class DisposableBeanLife implements DisposableBean {
    public DisposableBeanLife() {
        System.out.println("DisposableBeanLife");
    }

    /**
     * 在Bean释放时调用
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBeanLife: destroy");
    }
}
