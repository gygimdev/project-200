package hello.project.security;

import hello.project.domain.Member;
import hello.project.security.MemberDetails;
import hello.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // 회원 정보를 Database 에서 조회
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isEmpty()) {
            throw new UsernameNotFoundException(email + "사용자를 찾을수 없습니다.");
        }

        Member member = findMember.get();
        return new MemberDetails(member);
    }

    // 맴버 정보 변경시 보안정보 갱신
    public void updateMemberDetails(Member updatedMember) {
        MemberDetails updatedDetails = new MemberDetails(updatedMember);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(updatedDetails, authentication.getCredentials(), updatedDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
    }
}
