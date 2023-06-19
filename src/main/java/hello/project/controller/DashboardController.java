package hello.project.controller;

import hello.project.common.CurrentMemberDetail;
import hello.project.domain.Member;
import hello.project.dto.DashboardForm;
import hello.project.security.MemberDetails;
import hello.project.service.DashboardService;
import hello.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final MemberService memberService;
    private final DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String dashboardView(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {

        String loginMemberEmail = CurrentMemberDetail.getLoginMemberEmail(memberDetails);
        boolean flag = memberService.checkMemberHasHousehold(loginMemberEmail);
        if(!flag) {
            return "households";
        }
        DashboardForm form = dashboardService.getDashboard(loginMemberEmail);
        model.addAttribute("dashboardForm", form);
        return "dashboard";
    }

}
