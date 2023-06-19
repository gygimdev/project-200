package hello.project.dto;

import hello.project.domain.Item;
import hello.project.dto.ingredient.IngredientListDto;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class DashboardForm {

    private List<IngredientListDto> ingredients = new ArrayList<>();

    private List<Item> items = new ArrayList<>();
}
