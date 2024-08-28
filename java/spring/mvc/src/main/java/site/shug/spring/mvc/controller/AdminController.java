package site.shug.spring.mvc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/say")
    @PreAuthorize("hasRole('ADMIN')")
    String adminSay() {
        return "admin say";
    }
}
