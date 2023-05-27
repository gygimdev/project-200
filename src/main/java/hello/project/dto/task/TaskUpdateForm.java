package hello.project.dto.task;

import hello.project.domain.TaskStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class TaskUpdateForm {

    private Long id;

    @NotEmpty
    private String name;

    private String content;

    @NotEmpty
    private String status;

    @NotNull
    private LocalDateTime dueDate;

    public TaskUpdateForm(){};

    /** TaskDto 를 TaskUpdateForm 으로 변환
     * TaskDto -> TaskUpdateForm
     * @param taskDto
     */
    public TaskUpdateForm(TaskDto taskDto) {
        this.id = taskDto.getId();
        this.name = taskDto.getName();
        this.content = taskDto.getContent();
        this.status = statusToString(taskDto.getStatus());
        this.dueDate = taskDto.getDueDate();
    }

    private String statusToString(TaskStatus status) {
        return String.valueOf(status);
    }
}
