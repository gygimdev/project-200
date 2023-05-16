package hello.project.repository;

import hello.project.domain.Member;
import hello.project.dto.MemberForm;
import hello.project.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PublicKey;
import java.util.Optional;


@SpringBootTest
public class MemberRepositoryTest {


    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    public void 가입된회원검증() {
    }


}
