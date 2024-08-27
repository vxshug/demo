package site.shug.spring.javaweb.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * 使用WebListener注解, 并实现特定接口的类, 才能监听事件
 *
 * 分类
 * - ServletContextListener: 整个Web应用程序初始化完成后，以及Web应用程序关闭后获得回调通知
 * - HttpSessionListener: 监听HttpSession的创建和销毁事件；
 * - ServletRequestListener: 监听ServletRequest请求的创建和销毁事件；
 * - ServletRequestAttributeListener: 监听ServletRequest请求的属性变化事件（即调用ServletRequest.setAttribute()方法）；
 * - ServletContextAttributeListener: 监听ServletContext的属性变化事件（即调用ServletContext.setAttribute()方法）；
 */
@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("AppLister init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("AppListener destroy");
    }
}
