package hello.project;

import hello.project.domain.Member;
import hello.project.dto.HouseholdForm;
import hello.project.dto.member.MemberDto;
import hello.project.dto.member.MemberRegistrationForm;
import hello.project.dto.task.TaskCreateForm;
import hello.project.repository.HouseholdRepository;
import hello.project.repository.MemberRepository;
import hello.project.service.HouseholdService;
import hello.project.service.MemberService;
import hello.project.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final MemberService memberService;
    private final TaskService taskService;

    private final MemberRepository memberRepository;
    private final HouseholdService householdService;

    @Override
    public void run(String... args) throws Exception {

        // 맴버 생성
        String password = "Test1234!";
        String email = "gygim.dev@gmail.com";
        MemberDto dto = new MemberDto();
        dto.setEmail(email);
        memberService.RegisterMember(dto, password);
        Member findMember = memberRepository.findByEmail(email).get();

        // 가정 생성
        HouseholdForm householdForm = new HouseholdForm();
        householdForm.setName("familyA");
        householdService.createHousehold(householdForm, findMember);

        // 테스크 생성
        String loginMemberEmail = findMember.getEmail();
        LocalDateTime now = LocalDateTime.now();
        TaskCreateForm taskCreateForm1 = new TaskCreateForm();
        taskCreateForm1.setName("taskA");
        taskCreateForm1.setContent("taskAcontent");
        taskCreateForm1.setDueDate(now);
        TaskCreateForm taskCreateForm2 = new TaskCreateForm();
        taskCreateForm2.setName("taskB");
        taskCreateForm2.setContent("taskBcontent");
        taskCreateForm2.setDueDate(now);
        TaskCreateForm taskCreateForm3 = new TaskCreateForm();
        taskCreateForm3.setName("taskC");
        taskCreateForm3.setContent("taskCcontent");
        taskCreateForm3.setDueDate(now);
        taskService.createTask(loginMemberEmail, taskCreateForm1);
        taskService.createTask(loginMemberEmail, taskCreateForm2);
        taskService.createTask(loginMemberEmail, taskCreateForm3);
    }
}
