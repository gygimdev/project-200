package hello.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String greeting() {
        System.out.println("debug01");
        return "hello";
    }
}
