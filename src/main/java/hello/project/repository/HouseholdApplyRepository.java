package hello.project.repository;

import hello.project.domain.HouseholdApply;
import hello.project.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HouseholdApplyRepository extends JpaRepository<HouseholdApply, Long> {

    @Query("SELECT CASE WHEN COUNT(ha) > 0 THEN TRUE ELSE FALSE END" +
            " FROM HouseholdApply ha" +
            " WHERE ha.isApproved = 0" +
            " AND ha.member = :findMember")
    Boolean checkDuplicateHouseholdInvitation(@Param("findMember")Member findMember);

    @Query("SELECT ha" +
            " FROM HouseholdApply ha" +
            " WHERE ha.member = :findMember")
    HouseholdApply getHouseholdApplyByMember(@Param("findMember") Member member);
}
