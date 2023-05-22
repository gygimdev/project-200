package hello.project.dto.task;

import hello.project.domain.Task;
import hello.project.domain.TaskStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class TaskDto {

    Long id;
    String name;
    String content;
    TaskStatus status;
    LocalDateTime dueDate;

    /**
     * 업데이트 폼을 DTO 로 변환
     * @param form 업데이트폼
     */
    public TaskDto(TaskUpdateForm form) {
        this.id = form.getId();
        this.name = form.getName();
        this.content = form.getContent();
        this.status = TaskStatus.valueOf(form.getStatus());
        this.dueDate = LocalDateTime.parse(form.getDueDate());
    }

    public TaskDto(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.dueDate = task.getDueDate();
        this.content = task.getContent();
        this.status = task.getStatus();
    }
}
