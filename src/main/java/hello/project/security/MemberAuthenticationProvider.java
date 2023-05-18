package hello.project.security;

import hello.project.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberAuthenticationProvider implements AuthenticationProvider {
    private final MemberDetailService memberDetailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 입력된 회원 정보(email & password)
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        // 조회한 회원의 보안정보
        MemberDetails memberDetails = (MemberDetails) memberDetailService.loadUserByUsername(email);
        String dbPassword = memberDetails.getPassword();

        // 검증
        if (!passwordEncoder.matches(password, dbPassword)) {
            throw new BadCredentialsException("사용자 아이디가 일치하지 않습니다.");
        }

        Member member = memberDetails.getMember();
        return new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
