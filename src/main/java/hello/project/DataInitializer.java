package hello.project;

import hello.project.domain.Member;
import hello.project.dto.HouseholdForm;
import hello.project.dto.MemberRegistrationForm;
import hello.project.dto.TaskForm;
import hello.project.repository.HouseholdRepository;
import hello.project.repository.MemberRepository;
import hello.project.service.HouseholdService;
import hello.project.service.MemberService;
import hello.project.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final MemberService memberService;
    private final TaskService taskService;

    private final MemberRepository memberRepository;
    private final HouseholdRepository householdRepository;
    private final HouseholdService householdService;

    @Override
    public void run(String... args) throws Exception {


        // 맴버 생성
        MemberRegistrationForm memberForm = new MemberRegistrationForm();
        memberForm.setEmail("gygim.dev@gmail.com");
        memberForm.setPassword("Test1234!");
        Long findMemberId = memberService.RegisterMember(memberForm);
        Member findMember = memberRepository.findById(findMemberId)
                .orElseThrow(RuntimeException::new);

        // 가정 생성
        HouseholdForm householdForm = new HouseholdForm();
        householdForm.setName("familyA");
        householdService.createHousehold(householdForm, findMember);

        // 테스크 생성
        String loginMemberEmail = findMember.getEmail();
        TaskForm taskForm1 = new TaskForm();
        taskForm1.setName("taskA");
        taskForm1.setContent("taskAcontent");
        TaskForm taskForm2 = new TaskForm();
        taskForm2.setName("taskB");
        taskForm2.setContent("taskBcontent");
        TaskForm taskForm3 = new TaskForm();
        taskForm3.setName("taskC");
        taskForm3.setContent("taskCcontent");
        taskService.createTask(loginMemberEmail, taskForm1);
        taskService.createTask(loginMemberEmail, taskForm2);
        taskService.createTask(loginMemberEmail, taskForm3);
    }
}
