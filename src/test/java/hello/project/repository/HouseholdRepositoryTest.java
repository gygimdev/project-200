package hello.project.repository;

import hello.project.domain.Household;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class HouseholdRepositoryTest {

    @Autowired
    private HouseholdRepository householdRepository;

    @Test
    void saveHousehold() {

        //given
        String householdName = "개똥이네";
        Household household = Household.builder()
                .name(householdName)
                .build();

        //when
        Household savedHousehold = householdRepository.save(household);

        //then
        Assertions.assertThat(savedHousehold.getName()).isEqualTo(household.getName());
    }

    @Test
    void findHousehold() {
    }
}