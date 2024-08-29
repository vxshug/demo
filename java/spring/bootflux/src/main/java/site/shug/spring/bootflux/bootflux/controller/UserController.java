package site.shug.spring.bootflux.bootflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/author")
    Map<String, String> author() {
        Map<String, String> user = new HashMap<>();
        user.put("name", "shug");
        return user;
    }
}
