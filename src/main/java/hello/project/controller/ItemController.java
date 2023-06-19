package hello.project.controller;

import hello.project.common.CurrentMemberDetail;
import hello.project.dto.item.ItemCreateForm;
import hello.project.security.MemberDetails;
import hello.project.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /** 아이템 생성 폼 불러오기
     * @param model
     * @return
     */
    @GetMapping("/item/add")
    public String createItemFormView(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        String loginMemberEmail = CurrentMemberDetail.getLoginMemberEmail(memberDetails);
        ItemCreateForm form = itemService.getIngredients(loginMemberEmail);
        model.addAttribute("itemCreateForm", form);
        return "item/itemCreateForm";
    }

    /** 아이템 생성하기
     * @param memberDetails
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/item/add")
    public String createItemView(@AuthenticationPrincipal MemberDetails memberDetails, @ModelAttribute ItemCreateForm form, BindingResult bindingResult) {
        return "redirect:/dashboard";
    }
}
