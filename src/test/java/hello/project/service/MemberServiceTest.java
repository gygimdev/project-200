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
    void findMemberById() {
    }

    @Test
    void findAllMember() {
    }

}