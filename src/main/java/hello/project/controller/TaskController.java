package hello.project.controller;

import hello.project.domain.TaskDto;
import hello.project.dto.TaskForm;
import hello.project.security.MemberDetails;
import hello.project.service.TaskService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Task 디테일 불러오기
     */
    @GetMapping("/task/{taskId}")
    public String taskDetailView(@RequestParam Long taskId, Model model){

        return "task/update";


    }

    /**
     * Task 생성 불러오기
     */
    @GetMapping("/task/create")
    public String taskCreateFormView(Model model) {
        model.addAttribute("taskForm", new TaskForm());
        return "/task/taskForm";
    }

    /**
     * 작업 생성
     */
    @PostMapping("/task/create")
    public String createTaskView(@AuthenticationPrincipal MemberDetails memberDetails, @Valid TaskForm form) {
        String loginMemberEmail = memberDetails.getUsername();
        taskService.createTask(loginMemberEmail, form);
        return "redirect:/tasks";
    }

    /**
     * 작업 리스트
     */
    @GetMapping("/tasks")
    public String taskListView(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        List<TaskDto> tasks = taskService.getTaskList(memberDetails);
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
