package hello.project.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TaskForm {

    @NotEmpty
    private String name;

    private String content;

    private String status;

}
