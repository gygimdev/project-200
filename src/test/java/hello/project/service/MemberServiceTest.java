package hello.project.service;

import hello.project.dto.LoginForm;
import hello.project.dto.MemberDto;
import hello.project.dto.MemberForm;
import hello.project.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void 회원가입() {
    }

    @Test
    void 로그인() {
        //given
        MemberForm memberForm = new MemberForm();
        memberForm.setEmail("test@example.com");
        memberForm.setUsername("testUser");
        memberForm.setPassword("Test1234!");
        Long savedMemberId = memberService.RegisterMember(memberForm);

        //when
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail("test@example.com");
        loginForm.setPassword("Test1234!");
        Optional<MemberDto> findMemberDto = memberService.loginMember(loginForm);
        if (findMemberDto.isPresent()) {
            MemberDto findMember = findMemberDto.get();
        }
        else {
            System.out.println("::: debug ::: " + findMemberDto);
        }

        //then
//        Assertions.assertThat(savedMemberId).isEqualTo(findMember.getId());
    }

    @Test
    void findMemberById() {
    }

    @Test
    void findAllMember() {
    }

}