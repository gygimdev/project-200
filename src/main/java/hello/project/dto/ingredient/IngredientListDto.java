package hello.project.dto.ingredient;

import hello.project.domain.IngredientType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientListDto {

    private Long id;

    private String name;

    private String quantity;

    private IngredientType ingredientType;

    private Long expireIn;
}
