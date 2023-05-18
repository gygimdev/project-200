package hello.project;

import hello.project.dto.MemberForm;
import hello.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberDataInitializer implements CommandLineRunner {
    private final MemberService memberService;

    @Override
    public void run(String... args) throws Exception {
        MemberForm memberForm = new MemberForm();
        memberForm.setEmail("gygim.dev@gmail.com");
        memberForm.setPassword("Test1234!");
        memberService.RegisterMember(memberForm);
    }
}
