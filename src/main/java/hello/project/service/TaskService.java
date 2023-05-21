package hello.project.service;

import hello.project.domain.Household;
import hello.project.domain.Member;
import hello.project.domain.Task;
import hello.project.domain.TaskDto;
import hello.project.dto.TaskForm;
import hello.project.repository.MemberRepository;
import hello.project.repository.TaskRepository;
import hello.project.security.MemberDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository taskRepository;
    private final MemberRepository memberRepository;

    /**
     * Task 디테일 조회
     */
    public TaskDto getTaskDetail(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("해당 작업이 존재하지 않습니다."));
        return new TaskDto(task);
    }

    /**
     * Task 생성
     */
    @Transactional
    public void createTask(String loginMemberEmail, TaskForm taskForm) {
        Member findMember = memberRepository.findByEmail(loginMemberEmail)
                .orElseThrow(() -> new NoSuchElementException("맴버가 존재하지 않습니다."));
        Household household = findMember.getHousehold();
        String name = taskForm.getName();
        String content = taskForm.getContent();
        Task task = new Task(name, content, findMember, household);
        taskRepository.save(task);
    }

    /**
     * Task 리스트 조회
     */
    public List<TaskDto> getTaskList(MemberDetails memberDetails) {
        String loginMemberEmail = memberDetails.getUsername();
        List<Task> tasks = taskRepository.findTasksByMemberName(loginMemberEmail);
        return tasks.stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
    }
}
