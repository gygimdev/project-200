package hello.project.repository;

import hello.project.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient,Long>, IngredientRepositoryCustom {

    /** 가정(household)에 속해 있는 모든 재료(ingredient) 조회하기
     * @param memberName 로그인 회원 이메일
     * @return
     */
    @Query("SELECT i FROM Ingredient i JOIN i.household h JOIN h.members m Where m.email = :email and i.isDeleted=false")
    List<Ingredient> findIngredients(@Param("email") String memberName);
}
