package hello.project.dto.ingredient;

import hello.project.domain.Household;
import hello.project.domain.IngredientType;
import hello.project.domain.Member;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class IngredientDto {

    private Long id;

    private Household household;

    private Member member;

    private String name;

    private Integer quantity;

    private IngredientType ingredientType;

    private LocalDateTime expireDate;
}
