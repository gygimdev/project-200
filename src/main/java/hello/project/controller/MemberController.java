package hello.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @GetMapping("/member/register")
    public String registerView() {
        return "member/register";
    }

    @PostMapping("/member/register")
    public String register() {
        return "member/register";
    }

    @GetMapping("/member/login")
    public String loginView() {
        return "member/login";
    }

    @PostMapping("/member/login")
    public String login(){
        return "member/login";
    }
}
