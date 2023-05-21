package hello.project.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TaskDto {
    Long id;

    @NotEmpty
    String name;

    @NotEmpty
    String content;

    TaskStatus status;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.content = task.getContent();
        this.status = task.getStatus();
    }
}
