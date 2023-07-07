package hello.project.service;

import hello.project.domain.Member;
import hello.project.dto.member.MemberDto;
import hello.project.dto.member.MyInfoForm;
import hello.project.repository.MemberRepository;
import hello.project.security.MemberDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberDetailService memberDetailService;

    /** 나의 정보 업데이트
     *
     */
    @Transactional
    public void updateMyInfo(MemberDto dto) {
        Long memberId = dto.getId();
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("맴버가 존재하지 않습니다."));
        findMember.updateMember(dto.getUsername(), dto.getTimezone());
        memberRepository.save(findMember);
        memberDetailService.updateMemberDetails(findMember);
    }

    /** 나의 정보 불러오기
     * 나의 정보 페이지
     * @param loginMemberEmail 로그인유저이메일
     * @return MyInfoForm
     */
    public MyInfoForm getMyInfo(String loginMemberEmail) {

        Member findMember = memberRepository.findMyInfo(loginMemberEmail);
        log.info("logInfo {}", findMember.getUsername());
        return MyInfoForm.builder()
                .id(findMember.getId())
                .email(findMember.getEmail())
                .username(findMember.getUsername())
                .timezone(findMember.getTimezone())
                .householdName(findMember.getHousehold().getName())
                .build();
    }

    /**
     * 맴버가 가정을 가지고 있는지 체크
     */
    public boolean checkMemberHasHousehold(String loginMemberEmail) {
        Member findMember = memberRepository.findByEmail(loginMemberEmail)
                .orElseThrow(() -> new NoSuchElementException("맴버가 존재하지 않습니다."));
        Long count = memberRepository.countMemberHousehold(findMember.getEmail());
        return count > 0;
    }

    /**
     * 서비스 회원조회
     */
    public List<MemberDto> findAllMember() {
        List<Member> members = memberRepository.findAll();

        List<MemberDto> allMember = new ArrayList<>();
        for (Member member: members) {
            MemberDto memberDto = MemberDto.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .username(member.getUsername())
                    .timezone(member.getTimezone())
                    .build();
            allMember.add(memberDto);
        }
        return allMember;
    }

    /**
     * Member 회원가입
     */
    @Transactional
    public void RegisterMember(MemberDto dto, String password) {

        //비밀번호 암호화
        String encodedPassword = createEncodePassword(password);

        // 맴버엔티티 저장
        Member member = Member.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(encodedPassword)
                .timezone(dto.getTimezone())
                .build();

        memberRepository.save(member);
    }

    /**
     * 비밀번호 bCrypt 암호화
     * @param plainText 평문 비밀번호
     * @return hashing 된 비밀번호(String)
     */
    private String createEncodePassword(String plainText) {
        return passwordEncoder.encode(plainText);
    }

    private boolean checkEncodedPassword(String plainText, String encodedPassword) {
        return passwordEncoder.matches(plainText, encodedPassword);

    }

}
