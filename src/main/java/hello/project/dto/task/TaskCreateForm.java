package hello.project.dto.task;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskCreateForm {

    @NotEmpty
    private String name;

    private String content;

    private LocalDateTime dueDate;
}
