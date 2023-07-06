package hello.project.controller;

import hello.project.common.CurrentMemberDetail;
import hello.project.dto.DashboardForm;
import hello.project.security.MemberDetails;
import hello.project.service.DashboardService;
import hello.project.service.MemberService;
import hello.project.service.household.HouseholdApplyService;
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
    private final HouseholdApplyService householdApplyService;

    @GetMapping("/dashboard")
    public String dashboardView(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        String loginMemberEmail = CurrentMemberDetail.getLoginMemberEmail(memberDetails);

        // 맴버의 가정 신청이 진행중: 가정 신청 상태 페이지로 이동
        Boolean onGoingProcess = householdApplyService.duplicateCheck(loginMemberEmail);
        if (onGoingProcess) return "redirect:/household/apply-status";

        // 맴버가 가정 가입전: 맴버의 가정이 없을 경우 가정 리스트/생성 페이지로 이동
        boolean hasHousehold = memberService.checkMemberHasHousehold(loginMemberEmail);
        if(!hasHousehold) return "redirect:/households";

        // 맴버의 가정이 있는 경우: 맴버의 가정이 있는 경우에 기존 dashboard 페이지로 이동
        DashboardForm form = dashboardService.getDashboard(loginMemberEmail);
        model.addAttribute("dashboardForm", form);
        return "dashboard";
    }

}
