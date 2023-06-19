package hello.project.service;

import hello.project.domain.Ingredient;
import hello.project.domain.Member;
import hello.project.domain.Timezone;
import hello.project.dto.DashboardForm;
import hello.project.dto.ingredient.IngredientListDto;
import hello.project.repository.IngredientRepository;
import hello.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DashboardService {

    private final MemberRepository memberRepository;
    private final IngredientRepository ingredientRepository;

    @Transactional
    public DashboardForm getDashboard(String loginMemberEmail) {

        //재료리스트 조회
        List<Ingredient> ingredients = ingredientRepository.findIngredients(loginMemberEmail);

        //현재시간
        Instant now = Instant.now();
        ZoneId utcZoneId = ZoneId.of(Timezone.UTC.getTimeCode());
        LocalDateTime currentUtcLocalDateTime = now.atZone(utcZoneId).toLocalDateTime();

        // 재료 dto 변환
        List<IngredientListDto> ingredientsDtoList = new ArrayList<>();
        for(Ingredient i : ingredients) {
            LocalDateTime expireDate = i.getExpireDate();
            long days = Duration.between(currentUtcLocalDateTime, expireDate).toDays();

            IngredientListDto dto = IngredientListDto.builder()
                    .id(i.getId())
                    .ingredientType(i.getIngredientType())
                    .name(i.getName())
                    .quantity(i.getQuantity())
                    .expireIn(days)
                    .build();

            ingredientsDtoList.add(dto);
        }

        // DashboardForm 반환
        return DashboardForm.builder()
                .ingredients(ingredientsDtoList)
                .build();
    }


}
