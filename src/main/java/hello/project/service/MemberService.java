package hello.project.service;

import hello.project.domain.Language;
import hello.project.domain.Member;
import hello.project.domain.Timezone;
import hello.project.dto.member.MemberDto;
import hello.project.dto.member.MemberRegistrationForm;
import hello.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

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
     * 서비스 회원상세
     */
//    public MemberForm getMemberDetail(Long id){
//        Optional<Member> memberOptional = memberRepository.findById(id);
//        MemberForm memberForm = new MemberForm();
//        if (memberOptional.isPresent()) {
//            memberForm.setEmail(memberOptional.get().getEmail());
//            memberForm.setUsername(memberOptional.get().getUsername());
//        }
//        return memberForm;
//    }

    /**
     * 서비스 회원조회
     */
    public List<MemberDto> findAllMember() {
        List<Member> members = memberRepository.findAll();

        List<MemberDto> allMember = new ArrayList<>();
        for (Member member: members) {
            MemberDto memberDto = new MemberDto();
            memberDto.setId(member.getId());
            memberDto.setEmail(member.getEmail());
            memberDto.setUsername(member.getUsername());
            allMember.add(memberDto);
        }
        return allMember;
    }

    /**
     * Member 회원가입
     */
    @Transactional
    public void RegisterMember(MemberDto dto, String password) {
        String email = dto.getEmail();
        String username = dto.getUsername();
        Timezone timezone = dto.getTimezone();

        checkDuplicateMemberEmail(email);
        String encodedPassword = createEncodePassword(password);

        Member member = new Member(email, username, encodedPassword, timezone);
        memberRepository.save(member);
    }

    private void checkDuplicateMemberEmail(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if(findMember.isPresent()) {
            throw new IllegalStateException("이미 존재하는 유저입니다.");
        }
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
