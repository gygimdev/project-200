package hello.project.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskCreateForm {

    @NotEmpty
    private String name;

    private String content;

    @NotNull
    private LocalDateTime dueDate;
}
