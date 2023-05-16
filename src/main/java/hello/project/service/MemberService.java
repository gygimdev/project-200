package hello.project.service;

import hello.project.domain.Member;
import hello.project.dto.LoginForm;
import hello.project.dto.MemberDto;
import hello.project.dto.MemberForm;
import hello.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 로그인
     */
    public Optional<MemberDto> loginMember(LoginForm loginForm) {
        log.info("::: 로그인맴버 :::");
        String email = loginForm.getEmail();
        String plainText = loginForm.getPassword();

        Optional<Member> findMember = memberRepository.findByEmail(email);

        if (findMember.isPresent()) {
            Member member = findMember.get();
            if (checkEncodedPassword(plainText, member.getPassword())) {
                log.info("::: debug :::");
                MemberDto memberDto = new MemberDto();
                memberDto.setId(findMember.get().getId());
                memberDto.setEmail(findMember.get().getEmail());
                memberDto.setUsername(findMember.get().getUsername());
                return Optional.of(memberDto);
            }
        }
        return Optional.empty();
    }

    /**
     * 서비스 회원상세
     */
    public MemberForm findMemberById(Long id){
        Optional<Member> memberOptional = memberRepository.findById(id);
        MemberForm memberForm = new MemberForm();
        if (memberOptional.isPresent()) {
            memberForm.setEmail(memberOptional.get().getEmail());
            memberForm.setUsername(memberOptional.get().getUsername());
        }
        return memberForm;
    }

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
    public Long RegisterMember(MemberForm form) {
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
        return bCryptPasswordEncoder.encode(plainText);
    }

    private boolean checkEncodedPassword(String plainText, String encodedPassword) {
        return bCryptPasswordEncoder.matches(plainText, encodedPassword);

    }

}
