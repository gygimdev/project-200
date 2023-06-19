//package hello.project.controller;
//
//import hello.project.common.CurrentMemberDetail;
//import hello.project.domain.Member;
//import hello.project.domain.TaskSearchCond;
//import hello.project.domain.TaskSearchForm;
//import hello.project.domain.TaskStatus;
//import hello.project.dto.task.TaskDto;
//import hello.project.dto.task.TaskCreateForm;
//import hello.project.dto.task.TaskListForm;
//import hello.project.dto.task.TaskUpdateForm;
//import hello.project.security.MemberDetails;
//import hello.project.service.MemberService;
//import hello.project.service.TaskService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.List;
//import java.util.spi.CurrencyNameProvider;
//
//@Slf4j
//@Controller
//@RequiredArgsConstructor
//public class TaskController {
//    private final TaskService taskService;
//    private final MemberService memberService;
//
//    /**
//     * Task 내역(history)
//     */
//    @GetMapping("/task/history")
//    public String taskHistoryView(
//            @AuthenticationPrincipal MemberDetails memberDetails,
//            TaskSearchForm form, Model model) {
//
//        //로그인 유저 보안정보
//        Member loginMember = CurrentMemberDetail.getLoginMember(memberDetails);
//
//        // 검색 시작
//        TaskSearchCond taskSearchCond = new TaskSearchCond(form); // 검색조건
//        List<TaskDto> tasks = taskService.searchTaskHistory(taskSearchCond);
//
//        // 데이터 변환작업
//        TaskListForm taskListForm = new TaskListForm(tasks, loginMember);
//        model.addAttribute("taskListForm", taskListForm);
//        return "task/taskHistory";
//    }
//
//    /**
//     * Task 업데이트
//     * @param form TsakUpdateForm
//     * @return
//     */
//    @PostMapping("/task/update")
//    public String taskUpdateView(@AuthenticationPrincipal MemberDetails memberDetails, @Validated TaskUpdateForm form, BindingResult bindingResult){
//
//        Member loginMember = CurrentMemberDetail.getLoginMember(memberDetails);
//
//
//        if (bindingResult.hasErrors()) {
//            return "task/taskUpdateForm";
//        }
//
//        TaskDto dto = new TaskDto(form);
//        taskService.updateTask(dto, loginMember);
//        return "redirect:/tasks";
//    }
//
//    /** Task 상세페이지
//     * Task 상세 정보를 불러온다. 불러온 뒤에는 업데이트페이지로 사용된다.
//     * @param taskId: task_id
//     * @return taskUpdateForm
//     */
//    @GetMapping("/task/{taskId}")
//    public String taskDetailView(@AuthenticationPrincipal MemberDetails memberDetails, @PathVariable Long taskId, Model model){
//        // 로그인한 회원 보안정보
//        Member loginMember = CurrentMemberDetail.getLoginMember(memberDetails);
//
//        // 테스크 데이터 불러오기
//        TaskDto task = taskService.getTaskDetail(taskId);
//
//        // 업데이트 폼으로 변환
//        TaskUpdateForm form = new TaskUpdateForm(task, loginMember);
//        model.addAttribute("taskUpdateForm", form);
//        model.addAttribute("statusTypes", TaskStatus.values());
//
//        return "task/taskUpdateForm";
//    }
//
//    /** Task 생성 폼 불러오기
//     * Task 를 생성하기 위한 폼을 랜더링해준다.
//     * @return taskCreateForm.html
//     */
//    @GetMapping("/task/create")
//    public String taskCreateFormView(Model model) {
//        model.addAttribute("taskCreateForm", new TaskCreateForm());
//        return "task/taskCreateForm";
//    }
//
//    /** Task 생성
//     * Task 생성 폼에서 입력받은 데이터로 Task 를 생성한다.
//     * @param memberDetails 회원 보안정보
//     * @param form TaskCreateForm: Task 생성 폼
//     * @return 리다이랙트: /tasks
//     */
//    @PostMapping("/task/create")
//    public String createTaskView(@AuthenticationPrincipal MemberDetails memberDetails, @Validated TaskCreateForm form, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return "task/taskCreateForm";
//        }
//
//        //로그인 정보
//        String loginMemberEmail = CurrentMemberDetail.getLoginMemberEmail(memberDetails);
//
//        // TaskDto 로 변환
//        TaskDto dto = new TaskDto(form);
//
//        // 테스크 생성
//        taskService.createTask(loginMemberEmail, dto);
//        return "redirect:/tasks";
//    }
//
//
//    /** Task 리스트 템플릿
//     * 현재 유저가 매핑된 가정에 존재하는 task 정보들을 가져온다.
//     * @param memberDetails 회원 보안정보
//     * @return
//     */
//    @GetMapping("/tasks")
//    public String taskListView(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
//
//        // 로그인 맴버 보안정보
//        Member loginMember = CurrentMemberDetail.getLoginMember(memberDetails);
//        String loginMemberEmail = loginMember.getEmail();
//
//        // TaskListForm 으로 변환 작업
//        List<TaskDto> taskDtos = taskService.getTaskList(loginMemberEmail);
//
//        TaskListForm form = new TaskListForm(taskDtos, loginMember);
//        model.addAttribute("taskListForm", form);
//        return "task/taskList";
//    }
//
//    /**
//     * 작업 삭제
//     */
//
//    /**
//     * 작업 수정
//     */
//
//
//}
