package hello.project.controller;

import hello.project.domain.Language;
import hello.project.domain.Timezone;
import hello.project.dto.member.LoginForm;
import hello.project.dto.member.MemberDto;
import hello.project.dto.member.MemberRegistrationForm;
import hello.project.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /** Language Enum 등록
     * thymeleaf 에서 언어코드에 접근 할 수 있게 컨트롤러에 등록한다.
     * @return 언어코드의 배열
     */
    @ModelAttribute("timezones")
    public Timezone[] getTimezones() {
        return Timezone.values();
    }

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
        model.addAttribute("memberRegistrationForm", new MemberRegistrationForm());
        return "member/memberRegistrationForm";
    }

    /**
     * Member 회원가입
     */
    @PostMapping("/member/register")
    public String register(@Validated  @ModelAttribute MemberRegistrationForm form, BindingResult bidingResult) {
        String email = form.getEmail();

        if (bidingResult.hasErrors()) {
            System.out.println("bidingResult = " + bidingResult.getAllErrors());

            return "member/memberRegistrationForm";
        }

        MemberDto dto = new MemberDto();
        dto.setEmail(form.getEmail());
        dto.setUsername(form.getUsername());
        dto.setTimezone(form.getTimezone());

        String password = form.getPassword();
        memberService.RegisterMember(dto, password);

        return "redirect:/member/loginForm";
    }

    /** 로그인
     */
    @GetMapping("/member/login")
    public String loginForm(Model model) {
        log.info("::: loginForm :::");
        model.addAttribute("loginForm", new LoginForm());
        return "member/loginForm";
    }

    /** 로그아웃
     *
     */


}
