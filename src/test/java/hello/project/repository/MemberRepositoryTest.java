package hello.project.repository;

import hello.project.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void test() {

        String email = "gygim.dev@gmail.com";
        Member findMember = memberRepository.findByEmail(email).get();
        String code = findMember.getTimezone().getTimeCode();
        System.out.println("code = " + code);
    }
}