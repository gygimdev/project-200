package hello.project.repository;

import hello.project.domain.Member;
import hello.project.domain.Timezone;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 맴버저장() {
    }


    @Test
    void findByEmail() {
    }

    @Test
    void countMemberHousehold() {
    }

    @Test
    void findMyInfo() {
    }
}