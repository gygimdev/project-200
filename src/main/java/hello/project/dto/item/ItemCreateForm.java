package hello.project.dto.item;

import hello.project.dto.ingredient.IngredientListDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class ItemCreateForm {

    private Long id;

    @NotNull
    @Size(min = 2, max = 25)
    private String name;

    private String content;

    @NotNull
    @Range(min = 1, max = 9999)
    private Integer quantity;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expireDate;

    private List<IngredientListDto> ingredients = new ArrayList<>();
}
