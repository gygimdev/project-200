package hello.project.controller;

import hello.project.common.CurrentMemberDetail;
import hello.project.domain.Timezone;
import hello.project.dto.member.LoginForm;
import hello.project.dto.member.MemberDto;
import hello.project.dto.member.MemberRegistrationForm;
import hello.project.dto.member.MyInfoForm;
import hello.project.security.MemberDetails;
import hello.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    /** 나의 정보 수정
     * 내 정보 수정
     */
    @PostMapping("/member/myinfo")
    public String myInfoUpdateView(@ModelAttribute MyInfoForm form, Model model) {
        MemberDto dto = MemberDto.builder()
                .id(form.getId())
                .username(form.getUsername())
                .timezone(form.getTimezone())
                .build();

        memberService.updateMyInfo(dto);
        return "redirect:/dashboard";
    }

    /** 나의 정보
     * 내정보페이지
     * @param model
     * @return
     */
    @GetMapping("/member/myinfo")
    public String myInfoView(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        String loginMemberEmail = CurrentMemberDetail.getLoginMemberEmail(memberDetails);
        MyInfoForm myInfoForm = memberService.getMyInfo(loginMemberEmail);
        model.addAttribute("infoForm", myInfoForm);
        return "member/memberInfo";
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
     * Member 회원가입 폼 불러오기
     */
    @GetMapping("/member/register")
    public String registerForm(Model model) {
        model.addAttribute("memberRegistrationForm", new MemberRegistrationForm());
        return "member/memberRegistrationForm";
    }

    /**
     * Member 회원가입 시작
     */
    @PostMapping("/member/register")
    public String register(@Validated  @ModelAttribute MemberRegistrationForm form, BindingResult bidingResult) {

        if (bidingResult.hasErrors()) return "member/memberRegistrationForm";

        MemberDto dto = MemberDto.builder()
                .email(form.getEmail())
                .username(form.getUsername())
                .timezone(form.getTimezone())
                .build();

        memberService.RegisterMember(dto, form.getPassword());

        return "redirect:/member/loginForm";
    }

    /** 로그인
     */
    @GetMapping("/member/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "member/loginForm";
    }
}