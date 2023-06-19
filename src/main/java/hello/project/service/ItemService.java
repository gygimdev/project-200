package hello.project.service;

import hello.project.domain.Ingredient;
import hello.project.dto.ingredient.IngredientDto;
import hello.project.dto.ingredient.IngredientListDto;
import hello.project.dto.item.ItemCreateForm;
import hello.project.repository.IngredientRepository;
import hello.project.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final IngredientRepository ingredientRepository;

    @Transactional
    public ItemCreateForm getIngredients(String loginMemberEmail) {

        List<Ingredient> ingredients = ingredientRepository.findIngredients(loginMemberEmail);

        List<IngredientListDto> ingredientDtoList = ingredients.stream().map(i -> IngredientListDto.builder()
                        .id(i.getId())
                        .name(i.getName())
                        .quantity(i.getQuantity())
                        .build())
                .collect(Collectors.toList());

        return ItemCreateForm.builder()
                .ingredients(ingredientDtoList)
                .build();


    }

}
