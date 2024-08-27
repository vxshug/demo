package site.shug.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller 主要用于返回视图（通常是HTML、JSP等）或处理请求并返回模型数据。
 * ResponseBody 返回纯数据而不是视图
 * RestController = Controller + ResponseBody
 */
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }
}
