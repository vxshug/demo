package site.shug.spring.mvc.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class SayByeHandlerInterceptor implements HandlerInterceptor {
    /**
     * Controller方法调用前执行
     * @param request 当前请求
     * @param response 响应
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return false表示不再调用Controller, true表示继续执行
     * @throws Exception 异常信息
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在Controller处理前, 才能修改HttpServletResponse的Header
        response.setHeader("say", "bye");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * Controller方法正常返回后
     * @param request 当前请求
     * @param response 响应
     * @param handler Controller的方法
     * @param modelAndView Controller返回的ModelAndView, 如果Controller不是返回的ModelAndView, 则为null
     * @throws Exception 异常信息
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 无论Controller方法是否抛异常都会执行
     * @param request 当前请求
     * @param response Controller的方法
     * @param handler Controller的方法
     * @param ex Controller返回的异常
     * @throws Exception 异常信息
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
