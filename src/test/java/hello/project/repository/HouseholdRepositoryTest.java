package hello.project.repository;

import hello.project.domain.Household;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class HouseholdRepositoryTest {

    @Autowired
    private HouseholdRepository householdRepository;

    /** household 저장
     */
    @Test
    void saveHousehold() {
        //given
        String householdName = "개똥이네";
        Household household = Household.builder()
                .name(householdName)
                .build();

        //when
        Household savedHousehold = householdRepository.save(household);
        List<Household> all = householdRepository.findAll();

        //then
        assertThat(savedHousehold.getName()).isEqualTo(household.getName());
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    void findHousehold() {
    }
}