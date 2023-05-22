package hello.project.service;

import hello.project.domain.Member;
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

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

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
     * 회원가입
     */
    @Transactional
    public Long RegisterMember(MemberRegistrationForm form) {
        String email = form.getEmail();
        String username = form.getUsername();
        String plainText = form.getPassword();

        String encodedPassword = createEncodePassword(plainText);

        Member member = new Member(email, username, encodedPassword);
        memberRepository.save(member);
        return member.getId();
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
