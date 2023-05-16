package hello.project;

import hello.project.domain.Member;
import hello.project.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class MemberPrincipalDetailService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("::: 확인 ::: {}", email);

        Optional<Member> findMember = memberRepository.findByEmail(email);

        if (findMember.isEmpty()) {
            throw new UsernameNotFoundException(email + "사용자를 찾을수 없습니다.");
        }
        Member member = findMember.get();
        return new MemberPrincipalDetail(member);
    }
}
