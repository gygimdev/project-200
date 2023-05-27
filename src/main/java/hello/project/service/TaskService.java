package hello.project.service;

import hello.project.domain.Household;
import hello.project.domain.Member;
import hello.project.domain.Task;
import hello.project.domain.TaskSearchCond;
import hello.project.dto.task.TaskDto;
import hello.project.dto.task.TaskCreateForm;
import hello.project.repository.MemberRepository;
import hello.project.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository taskRepository;
    private final MemberRepository memberRepository;

    public List<TaskDto> searchTaskHistory(TaskSearchCond cond) {
        List<Task> tasks = taskRepository.startQuery(cond);
        List<TaskDto> dtos = new ArrayList<>();
        for (Task task : tasks) {
            TaskDto dto = new TaskDto(task);
            dtos.add(dto);
        }
        return dtos;

    }

    @Transactional
    public void updateTask(TaskDto dto) {
        Long taskId = dto.getId();
        Task findTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("해당 작업이 존재하지 않습니다."));

        Task updatedTask = findTask.updateTask(dto);
        taskRepository.save(updatedTask);
    }

    /**
     * Task 디테일 조회
     */
    public TaskDto getTaskDetail(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("해당 작업이 존재하지 않습니다."));
        return new TaskDto(task);
    }

    /**
     * Task 생성 비즈니스 로직
     * @param loginMemberEmail 로그인된 회원 이메일
     * @param dto TaskDto
     *
     */
    @Transactional
    public void createTask(String loginMemberEmail, TaskDto dto) {
        // 이메일로 회원 조회
        Member findMember = memberRepository.findByEmail(loginMemberEmail)
                .orElseThrow(() -> new NoSuchElementException("맴버가 존재하지 않습니다."));

        // 가정 조회
        Household household = findMember.getHousehold();

        // 테스크 생성
        Task task = new Task(dto, findMember, household);
        taskRepository.save(task);
    }

    /**
     * Task 리스트 조회
     * Household 에 속한 모든 Task 를 가져온다.
     * @param loginMemberEmail
     * @return
     */
    public List<TaskDto> getTaskList(String loginMemberEmail) {
        List<Task> tasks = taskRepository.findAllTaskBelongMemberHousehold(loginMemberEmail);
        return tasks.stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
    }
}
