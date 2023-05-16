package hello.project.service;

import hello.project.domain.Member;
import hello.project.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    public void 회원가입() {

        //given
        Member member = new Member("amusesla");
        memberRepository.save(member);

        //when
        Member findMember = memberRepository.findByEmail("amusesla");

        //then
        System.out.println("findMember.getMemberId() = " + findMember.getId());
        System.out.println("member = " + member.getId());

        assertThat(member.getId()).isEqualTo(findMember.getId());
    }


}