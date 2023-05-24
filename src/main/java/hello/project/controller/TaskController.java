package hello.project.controller;

import hello.project.common.CurrentMemberDetail;
import hello.project.domain.TaskStatus;
import hello.project.dto.task.TaskDto;
import hello.project.dto.task.TaskCreateForm;
import hello.project.dto.task.TaskListForm;
import hello.project.dto.task.TaskUpdateForm;
import hello.project.security.MemberDetails;
import hello.project.service.TaskService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Task 정보 업데이트
     * @param form TsakUpdateForm
     * @return
     */
    @PostMapping("/task/update")
    public String taskUpdateView(@Valid TaskUpdateForm form){
        log.info("컨트롤러 아이디 {}", form.getId());
        TaskDto dto = new TaskDto(form);
        taskService.updateTask(dto);
        return "redirect:/tasks";
    }


    /**
     * Task 디테일 불러오기 후 업데이트
     * @param taskId: task_id
     * @return taskUpdateForm
     */
    @GetMapping("/task/{taskId}")
    public String taskDetailView(@PathVariable Long taskId, Model model){
        TaskDto task = taskService.getTaskDetail(taskId);
        TaskUpdateForm form = new TaskUpdateForm(task);
        log.info("::::아이디넘겨주기 {}", form.getId());
        model.addAttribute("taskUpdateForm", form);

        model.addAttribute("statusTypes", TaskStatus.values());

        return "task/taskUpdateForm";
    }

    /**
     * TaskCreateForm 불러오기
     * @return taskCreateForm.html
     */
    @GetMapping("/task/create")
    public String taskCreateFormView(Model model) {
        model.addAttribute("taskCreateForm", new TaskCreateForm());
        return "/task/taskCreateForm";
    }

    /**
     * Task 생성
     * @param memberDetails 회원 보안정보
     * @param form TaskCreateForm: Task 생성 폼
     * @return 리다이랙트: /tasks
     */
    @PostMapping("/task/create")
    public String createTaskView(@AuthenticationPrincipal MemberDetails memberDetails, @Validated TaskCreateForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            // 에러 메시지를 콘솔에 출력
            bindingResult.getAllErrors()
                    .forEach(error -> {
                        log.info(":::: 오브젝트 이름: {}", error.getObjectName());
                        log.info(":::: 에러 필드: {}", error.getCode());
                        log.info(":::: 에러 필드: {}", error.getCodes());
                    });
        }

        if (bindingResult.hasErrors()) {
            return "/task/taskCreateForm";
        }
        String loginMemberEmail = CurrentMemberDetail.getLoginMemberEmail(memberDetails);
        taskService.createTask(loginMemberEmail, form);
        return "redirect:/tasks";
    }


    /**
     * Task 리스트 조회
     *  Household 에 등록된 모든 Task 들을 가져온다.
     * @param memberDetails 회원 보안정보
     * @return
     */
    @GetMapping("/tasks")
    public String taskListView(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        String loginMemberEmail = CurrentMemberDetail.getLoginMemberEmail(memberDetails);
        List<TaskDto> taskDtos = taskService.getTaskList(loginMemberEmail);
        List<TaskListForm> tasks = taskDtos.stream()
                .map(TaskListForm::new)
                .collect(Collectors.toList());
        model.addAttribute("tasks", tasks);
        return "task/taskList";
    }

    /**
     * 작업 삭제
     */

    /**
     * 작업 수정
     */


}
