package hello.project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Ingredient extends AuditableEntity {

    protected Ingredient() {};

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ingredient_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id")
    private Household household;

    @NotNull
    @Column(columnDefinition = "BOOLEAN")
    @Builder.Default
    private Boolean isDeleted = false;

    @NotNull
    @Size(min=1, max=25)
    private String name;

    @NotNull
    @Size(min=1, max=5)
    private String quantity;

    @NotNull
    @Enumerated(EnumType.STRING)
    private IngredientType ingredientType;

    @NotNull
    private LocalDateTime expireDate;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    /** 재료정보 업데이트
     * @param name 재료이름
     * @param quantity 재료 수량
     * @param type 재료 종류
     * @param expireDate 재료 유통기한
     */
    public Ingredient updateIngredientInfo(String name, String quantity, IngredientType type, LocalDateTime expireDate) {
        this.name = name;
        this.quantity = quantity;
        this.ingredientType = type;
        this.expireDate = expireDate;
        return this;
    }

    /** 재료정보 삭제
     * @return Ingredient
     */
    public Ingredient deleteIngredientInfo() {
        this.isDeleted = true;
        return this;
    }

}
