package hello.project.controller;

import hello.project.common.CurrentMemberDetail;
import hello.project.domain.IngredientType;
import hello.project.dto.ingredient.*;
import hello.project.security.MemberDetails;
import hello.project.service.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    /** Ingredient Enum 등록
     * thymeleaf 에서 itemEnum 에 접근할수 있게 한다.
     */
    @ModelAttribute("ingredientTypes")
    public IngredientType[] getIngredientTypes() {
        return IngredientType.values();
    }

    /** 재료 삭제
     *
     */
    @PostMapping("/ingredient/delete")
    public String ingredientDeleteView(@RequestParam("id") Long ingredientId) {
        ingredientService.deleteIngredient(ingredientId);
        return "redirect:/dashboard";
    }

    /** 재료 수정
     */
    @PostMapping("/ingredient/update")
    public String ingredientUpdateView(@ModelAttribute IngredientUpdateForm form, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "ingredient/ingredientUpdateForm";
        }

        // dto 변환
        IngredientDto dto = IngredientDto.builder()
                .id(form.getId())
                .name(form.getName())
                .quantity(form.getQuantity())
                .ingredientType(form.getIngredientType())
                .expireDate(form.getExpireDate())
                .build();

        // 서비스 호출
        ingredientService.updateIngredient(dto);
        return "redirect:/dashboard";
    }


    /** 재료 디테일/수정 폼 조회
     *
     */
    @GetMapping("/ingredient/{ingredientId}")
    public String ingredientUpdateFormView(@PathVariable("ingredientId") Long id, Model model) {
        IngredientDto ingredientDto = ingredientService.getIngredientDetail(id);

        // Dto -> Form 으로 변환
        IngredientUpdateForm form = IngredientUpdateForm.builder()
                .id(ingredientDto.getId())
                .name(ingredientDto.getName())
                .quantity(ingredientDto.getQuantity())
                .ingredientType(ingredientDto.getIngredientType())
                .expireDate(ingredientDto.getExpireDate())
                .build();

        model.addAttribute("ingredientUpdateForm", form);
        return "ingredient/ingredientUpdateForm";
    }

    /** 재료 생성 폼
     *
     */
    @GetMapping("/ingredient/add")
    public String AddItemFormView(Model model) {
        model.addAttribute(new IngredientCreateForm());
        return "ingredient/ingredientCreateForm";
    }

    /** 재료 생성
     */
    @PostMapping("/ingredient/add")
    public String AddItemView(@AuthenticationPrincipal MemberDetails memberDetails, @ModelAttribute IngredientCreateForm form, BindingResult bindingResult) {

        String loginMemberEmail = CurrentMemberDetail.getLoginMemberEmail(memberDetails);
        if(bindingResult.hasErrors()) {
            return "ingredient/ingredientCreateForm";
        }

        ingredientService.createIngredient(form, loginMemberEmail);

        return "redirect:/dashboard";
    }
}
