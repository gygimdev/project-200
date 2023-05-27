package hello.project.dto.task;

import hello.project.domain.Task;
import hello.project.domain.TaskStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TaskDto {

    Long id;
    String name;
    String content;
    TaskStatus status;
    LocalDateTime dueDate;


    /** TaskCreateForm 을 TaskDto 로 변환
     * TaskCreateForm -> TaskDto
     *
     */
    public TaskDto(TaskCreateForm form) {
        this.name = form.getName();
        this.content = form.getContent();
        this.dueDate = form.getDueDate();
    }

    /** TaskUpdateForm 을 TaskDto 로 변환
     * TaskUpdateForm -> TaskDto
     * @param form 업데이트폼
     */
    public TaskDto(TaskUpdateForm form) {
        this.id = form.getId();
        this.name = form.getName();
        this.content = form.getContent();
        this.status = TaskStatus.valueOf(form.getStatus());
        this.dueDate = form.getDueDate();
    }

    public TaskDto(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.dueDate = task.getDueDate();
        this.content = task.getContent();
        this.status = task.getStatus();
    }
}
