package hello.project.dto.ingredient;

import hello.project.domain.IngredientType;
import hello.project.validator.ValidEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Data
@Builder
public class IngredientUpdateForm {

    @NotNull
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    @Range(min=1, max = 9999)
    private Integer quantity;

    @ValidEnum(enumClass = IngredientType.class)
    private IngredientType ingredientType;

    @NotEmpty
    private LocalDateTime expireDate;
}
