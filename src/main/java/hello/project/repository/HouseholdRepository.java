package hello.project.repository;

import hello.project.domain.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseholdRepository extends JpaRepository<Household, Long> {

    @Query("select h from Household h where h.name like %:name%")
    public List<Household> findHousehold(@Param("name") String name);
}


