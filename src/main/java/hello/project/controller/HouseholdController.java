package hello.project.controller;

import hello.project.common.CurrentMemberDetail;
import hello.project.domain.Household;
import hello.project.domain.Member;
import hello.project.dto.HouseholdDto;
import hello.project.dto.HouseholdForm;
import hello.project.security.MemberDetails;
import hello.project.service.HouseholdService;
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


    /**
     * 맴버가 가정 합류
     * @return
     */
    @PostMapping("/household/join")
    public String joinHousehold(@AuthenticationPrincipal MemberDetails memberDetails, @RequestParam("id") Long householdId) {
        householdService.joinHousehold(memberDetails, householdId);
        return "redirect:/tasks";
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
        return "redirect:/tasks";
    }

    /**
     * 가정 리스트 조회
     */
    @GetMapping("/households")
    public String householdListView(Model model) {
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
