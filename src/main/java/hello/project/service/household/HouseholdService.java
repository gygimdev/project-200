package hello.project.service.household;

import hello.project.domain.Household;
import hello.project.domain.HouseholdApply;
import hello.project.domain.Member;
import hello.project.dto.household.HouseholdDto;
import hello.project.dto.HouseholdForm;
import hello.project.repository.HouseholdApplyRepository;
import hello.project.repository.HouseholdRepository;
import hello.project.repository.MemberRepository;
import hello.project.security.MemberDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HouseholdService {
    private final HouseholdApplyRepository householdApplyRepository;
    private final HouseholdRepository householdRepository;
    private final MemberRepository memberRepository;

    /**
     * 맴버가 household 에 신청
     */
    @Transactional
    public void applyHousehold(String loginMemberEmail, Long householdId) {
        // 가정 신청 로직 시작
        Member findMember = memberRepository.findByEmail(loginMemberEmail)
                .orElseThrow(() -> new NoSuchElementException("Member not found"));

        Household findHousehold = householdRepository.findById(householdId)
                .orElseThrow(() -> new NoSuchElementException("Household not found"));

        HouseholdApply householdApply = HouseholdApply.builder()
                .member(findMember)
                .household(findHousehold)
                .build();
        householdApplyRepository.save(householdApply);
    }

    /**
     * 맴버가 household 에 조인
     */
    @Transactional
    public void joinHousehold(MemberDetails memberDetails, Long householdId) {
        Member loginMemberInfo = memberDetails.getMember();
        Member findMember = memberRepository.findByEmail(loginMemberInfo.getEmail())
                .orElseThrow(() -> new NoSuchElementException("Member not found"));
        Household findhousehold = householdRepository.findById(householdId)
                .orElseThrow(() -> new NoSuchElementException("Household not found"));
        findMember.changeHousehold(findhousehold);
    }

    /**
     * household 생성
     */
    @Transactional
    public void createHousehold(HouseholdForm form, Member member) {
        String householdName = form.getName();
        Household household = Household.builder()
                .name(householdName)
                .build();
        Household savedHousehold = householdRepository.save(household);
        Member foundMember = memberRepository.findByEmail(member.getEmail())
                .orElseThrow(() -> new NoSuchElementException("Member not found"));
        foundMember.changeHousehold(savedHousehold);
    }

    /** household 리스트 전체 조회
     */
    public List<HouseholdDto> getHouseholdsList() {
        List<Household> households = householdRepository.findAll();
        return households.stream()
                .map(household -> {
                    HouseholdDto householdDto = new HouseholdDto();
                    householdDto.setId(household.getId());
                    householdDto.setName(household.getName());
                    return householdDto;
                }).collect(Collectors.toList());
    }

    /**
     * household 이름으로 검색
     */
    public List<Household>  searchHousehldByName(String householdName) {
        List<Household> all = householdRepository.findHousehold(householdName);
        log.info(":::: household 서비스: {}", all);
        return all;
    }
}
