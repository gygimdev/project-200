package hello.project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/css/*", "/js/*", "https://unpkg.com/*", "/member/register", "/api/*", "/error", "/error/*").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/member/login")
                        // 로그인 성공시 redirect 되는 페이지
                        .defaultSuccessUrl("/dashboard", true)
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .permitAll()
                )
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                    .sessionFixation().migrateSession()
                    .invalidSessionUrl("/login?expire")
                    .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                        .expiredUrl("/login?expire")
                        .and()
                    .and()
                .logout()
                    .logoutUrl("/logout") // 로그아웃 URL 설정
                    .logoutSuccessUrl("/login?logout") // 로그아웃 후 리다이렉트할 URL 설정
                    .invalidateHttpSession(true) // HttpSession 무효화
                    .deleteCookies("JSESSIONID"); // 세션과 관련된 쿠키 삭제

        return http.build();
    }
}
