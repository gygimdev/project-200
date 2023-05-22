package hello.project.dto.task;

import hello.project.domain.TaskStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class TaskUpdateForm {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String content;

    @NotEmpty
    private String status;

    @NotEmpty
    private String dueDate;

    public TaskUpdateForm(){};

    public TaskUpdateForm(TaskDto taskDto) {
        this.id = taskDto.getId();
        this.name = taskDto.getName();
        this.content = taskDto.getContent();
        this.status = statusToString(taskDto.getStatus());
        this.dueDate = formatDueDate(taskDto.getDueDate());
    }

    private String statusToString(TaskStatus status) {
        return String.valueOf(status);
    }

    private String formatDueDate(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("YYYY-MM-dd'T'HH:mm"));
    }
}
