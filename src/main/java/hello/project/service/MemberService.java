package hello.project.service;

import hello.project.domain.Member;
import hello.project.repository.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long RegisterMember() {
        Member member = new Member("amusesla");
        memberRepository.save(member);
        return member.getId();
    }

}
