package hello.project.controller;

import hello.project.dto.LoginForm;
import hello.project.dto.MemberDto;
import hello.project.dto.MemberRegistrationForm;
import hello.project.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 상세
     */
//    @GetMapping("/member/{memberId}")
//    public String memberDetailView(@PathVariable Long memberId, Model model) {
//        MemberDetailForm memberForm = memberService.getMemberDetail(memberId);
//        model.addAttribute("member", memberForm);
//        return "member/memberDetail";
//    }

    /**
     * Member 회원 리스트 조회
     */
    @GetMapping("/members")
    public String memberListView(Model model) {
        List<MemberDto> members = memberService.findAllMember();
        model.addAttribute("members", members);
        return "member/memberList";
    }

    /**
     * Member 회원가입 폼
     */
    @GetMapping("/member/register")
    public String registerForm(Model model) {
        model.addAttribute("registrationForm", new MemberRegistrationForm());
        return "member/memberRegistrationForm";
    }

    /**
     * Member 회원가입
     */
    @PostMapping("/member/register")
    public String register(@Valid MemberRegistrationForm form) {
        memberService.RegisterMember(form);
        return "redirect:/members";
    }

    /**
     * 로그인 폼
     */
    @GetMapping("/member/login")
    public String loginForm(Model model) {
        log.info("::: loginForm :::");
        model.addAttribute("loginForm", new LoginForm());
        return "member/loginForm";
    }
}
