package hello.project.repository;

import hello.project.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query("SELECT count(h) FROM Member m JOIN m.household h WHERE m.email = :email")
    Long countMemberHousehold(@Param("email") String email);

    @Query("SELECT m FROM Member m  JOIN FETCH m.household h WHERE m.email = :email")
    Member findMyInfo(@Param("email") String email);

}
