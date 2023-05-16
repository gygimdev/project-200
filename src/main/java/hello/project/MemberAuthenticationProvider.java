package hello.project;

import hello.project.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MemberAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MemberPrincipalDetailService memberPrincipalDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        MemberPrincipalDetail memberPrincipalDetail = (MemberPrincipalDetail) memberPrincipalDetailService.loadUserByUsername(email);

        // DB에 저장된 password
        String dbPassword = memberPrincipalDetail.getPassword();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!passwordEncoder.matches(password, dbPassword)) {

            throw new BadCredentialsException("사용자 아이디가 일치하지 않습니다.");
        }

        Member member = memberPrincipalDetail.getMember();
        return new UsernamePasswordAuthenticationToken(memberPrincipalDetail, null, memberPrincipalDetail.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
