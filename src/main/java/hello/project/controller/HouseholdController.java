package hello.project.controller;

import hello.project.common.CurrentMemberDetail;
import hello.project.domain.Household;
import hello.project.domain.Member;
import hello.project.dto.household.HouseholdApplyForm;
import hello.project.dto.household.HouseholdDto;
import hello.project.dto.HouseholdForm;
import hello.project.security.MemberDetails;
import hello.project.service.household.HouseholdApplyService;
import hello.project.service.household.HouseholdService;
import hello.project.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HouseholdController {

    private final HouseholdService householdService;
    private final HouseholdApplyService householdApplyService;
    private final MemberService memberService;

    /** 가정 합류 취소
     *
     */
    @PostMapping("/household/apply-cancel")
    public String householdApplyCancel(@RequestParam("householdApplyId") Long householdApplyId) {
        householdApplyService.cancelHouseholdApply(householdApplyId);
        return "redirect:/households";
    }

    /** 가정 합류 신청 조회
     *
     */
    @GetMapping("/household/apply-status")
    public String householdApplyStatus(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        String loginMemberEmail = CurrentMemberDetail.getLoginMemberEmail(memberDetails);
        HouseholdApplyForm form = householdApplyService.getMemberHouseholdApply(loginMemberEmail);
        model.addAttribute("householdApplyForm", form);
        return "household/householdApplyForm";
    }

    /** 가정 합류 신청
     */
    @PostMapping("/household/apply")
    public String applyHousehold(@AuthenticationPrincipal MemberDetails memberDetails, @RequestParam("id") Long householdId, Model model) {
        String loginMemberEmail = CurrentMemberDetail.getLoginMemberEmail(memberDetails);
        householdService.applyHousehold(loginMemberEmail, householdId);
//        HouseholdInvitationStatusForm form = HouseholdInvitationStatusForm.builder()
//                .message(message)
//                .build();

//        model.addAttribute("householdInvitationStatusForm", )

        return "household/householdInvitationStatus";
    }

    /**
     * 맴버가 가정 합류
     * @return
     */
    @PostMapping("/household/join")
    public String joinHousehold(@AuthenticationPrincipal MemberDetails memberDetails, @RequestParam("id") Long householdId) {
        householdService.joinHousehold(memberDetails, householdId);
        return "redirect:/ingredients";
    }

    /**
     * 가정 생성 폼
     */
    @GetMapping("/household/create")
    public String householdForm(Model model) {
        model.addAttribute("householdForm", new HouseholdForm());
        return "household/householdForm";
    }

    /**
     * 가정 생성 로직
     * @param memberDetails 로그인된 맴버 보안 정보
     * @param form MemberForm 정보
     * @return /tasks 롤 리다이랙트
     */
    @PostMapping("/household/create")
    public String householdFormCreate(@AuthenticationPrincipal MemberDetails memberDetails, @Valid HouseholdForm form) {
        Member loginMember = CurrentMemberDetail.getLoginMember(memberDetails);
        householdService.createHousehold(form, loginMember);
        return "redirect:/ingredients";
    }

    /**
     * 가정 리스트 조회
     */
    @GetMapping("/households")
    public String householdListView(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        String loginUserEmail = memberDetails.getUsername();


        // 맴버가 이미 가정에 배정되어 있으면 Task 리스트 화면으로
        boolean flag = memberService.checkMemberHasHousehold(loginUserEmail);
        if(flag) return "redirect:/ingredients";

        List<HouseholdDto> householdsList = householdService.getHouseholdsList();
        model.addAttribute("households", householdsList);
        return "household/householdList";
    }

    /**
     * 가정 검색
     * @param keyword 검색 키워드
     */
    @GetMapping("/household/search")
    public String householdSearch(@RequestParam String keyword, Model model) {
        List<Household> households = householdService.searchHousehldByName(keyword);
        model.addAttribute("households", households);
        return "household/householdList";
    }
}
