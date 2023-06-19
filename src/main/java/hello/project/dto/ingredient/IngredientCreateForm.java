package hello.project.dto.ingredient;

import hello.project.domain.IngredientType;
import hello.project.validator.ValidEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class IngredientCreateForm {

    @NotNull
    @Size(min = 2, max = 25)
    private String name;

    @NotNull
    @Range(min = 1, max = 9999)
    private Integer quantity;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expireDate;

    @NotNull
    @ValidEnum(enumClass = IngredientType.class)
    private IngredientType ingredientType;
}
