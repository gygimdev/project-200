package hello.project.service.household;

import hello.project.domain.HouseholdApply;
import hello.project.domain.Member;
import hello.project.dto.household.HouseholdApplyForm;
import hello.project.repository.HouseholdApplyRepository;
import hello.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HouseholdApplyService {

    private final HouseholdApplyRepository householdApplyRepository;
    private final MemberRepository memberRepository;

    /** household 신청 취소
     * 맴버의 household 가입 신청을 취소한다.
     */
    @Transactional
    public void cancelHouseholdApply(Long householdApplyId) {
        householdApplyRepository.deleteById(householdApplyId);
    }



    /** household 신청 중복 체크
     * @param loginMemberEmail 로그인 맴버 이메일
     * @return
     */
    public Boolean duplicateCheck(String loginMemberEmail) {
        Member findMember = memberRepository.findByEmail(loginMemberEmail)
                .orElseThrow(() -> new NoSuchElementException("member not found"));
        return householdApplyRepository.checkDuplicateHouseholdInvitation(findMember);
    }

    /** household 신청 내역 조회
     * 맴버의 household 신청 내역을 조회한다.
     */
    public HouseholdApplyForm getMemberHouseholdApply(String loginMemberEmail) {
        Member findMember = memberRepository.findByEmail(loginMemberEmail)
                .orElseThrow(() -> new NoSuchElementException("member not found"));
        HouseholdApply findHouseholdApply = householdApplyRepository.getHouseholdApplyByMember(findMember);

        return HouseholdApplyForm.builder()
                .householdApplyId(findHouseholdApply.getId())
                .householdName(findHouseholdApply.getHousehold().getName())
                .createdAt(findHouseholdApply.getCreated_at())
                .build();
    }
}
