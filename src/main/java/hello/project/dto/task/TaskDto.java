package hello.project.dto.task;

import hello.project.domain.Task;
import hello.project.domain.TaskStatus;
import lombok.Data;

import java.time.*;

@Data
public class TaskDto {

    private Long id;
    private String name;
    private String content;
    private TaskStatus status;
    private LocalDateTime dueDate;


    /** Task 생성
     * Task 생성시 받은 폼(TaskCreateForm) 데이터를 Dto 로 변환한다.
     *
     */
    public TaskDto(TaskCreateForm form) {
        this.name = form.getName();
        this.content = form.getContent();
        this.dueDate = form.getDueDate();
    }

    /** Task 업데이트
     * Task 업데이트시 받은 폼(TaskUpdateForm) 데이터를 Dto 로 변환한다.
     * @param form 업데이트폼
     */
    public TaskDto(TaskUpdateForm form) {
        this.id = form.getId();
        this.name = form.getName();
        this.content = form.getContent();
        this.status = TaskStatus.valueOf(form.getStatus());
        this.dueDate = form.getDueDate();
    }

    /** Task 조회
     * db 에서 조회한 데이터를 dto 로 변환한다.
     * @param task
     */
    public TaskDto(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.dueDate = task.getDueDate();
        this.content = task.getContent();
        this.status = task.getStatus();
    }
}
