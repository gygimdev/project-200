package hello.project.service;

import hello.project.common.DateTimeUtil;
import hello.project.domain.Ingredient;
import hello.project.domain.Member;
import hello.project.domain.Timezone;
import hello.project.dto.ingredient.IngredientCreateForm;
import hello.project.dto.ingredient.IngredientDto;
import hello.project.repository.IngredientRepository;
import hello.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.Date;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final MemberRepository memberRepository;

    /** 재료 삭제하기
     *
     */
    @Transactional
    public void deleteIngredient(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ingredient Not Found"));
        Ingredient deletedIngredient = ingredient.deleteIngredientInfo();
        ingredientRepository.save(deletedIngredient);
    }

    /** 재료 수정하기
     */
    @Transactional
    public void updateIngredient(IngredientDto dto){
        Ingredient ingredient = ingredientRepository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("재료 정보가 없습니다."));

        Ingredient updatedIngredient  = ingredient.updateIngredientInfo(dto.getName(),
                String.valueOf(dto.getQuantity()),
                dto.getIngredientType(),
                dto.getExpireDate());

        ingredientRepository.save(updatedIngredient);
    }

    /** 재료 디테일 불러오기
     *
     */
    public IngredientDto getIngredientDetail(Long id) {
        Ingredient ingredient =  ingredientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("재료 정보가 없습니다."));

        return IngredientDto.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .quantity(Integer.valueOf(ingredient.getQuantity()))
                .ingredientType(ingredient.getIngredientType())
                .expireDate(ingredient.getExpireDate())
                .build();
    }

    /** 재료 생성하기
     */
    @Transactional
    public void createIngredient(IngredientCreateForm form, String loginMemberEmail) {

        Member findMember = memberRepository.findByEmail(loginMemberEmail)
                .orElseThrow(() -> new NoSuchElementException("맴버가 존재하지 않습니다."));

        String originTimezoneCode = findMember.getTimezone().getTimeCode();
        Date expireDate = form.getExpireDate();
        LocalDateTime localDateTime = DateTimeUtil.dateToLocalDateTime(expireDate, originTimezoneCode);
        LocalDateTime utcLocalDateTime = DateTimeUtil.convertLocalDateTime(localDateTime, originTimezoneCode, Timezone.UTC.getTimeCode());

        Ingredient ingredient = Ingredient.builder()
                .household(findMember.getHousehold())
                .member(findMember)
                .name(form.getName())
                .quantity(String.valueOf(form.getQuantity()))
                .ingredientType(form.getIngredientType())
                .expireDate(utcLocalDateTime)
                .build();

        ingredientRepository.save(ingredient);
    }
}
