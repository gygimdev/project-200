package hello.project.dto.task;

import hello.project.domain.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class TaskListForm {

    private Long id;

    private String name;

    private String content;

    private String status;

    private String dueDate;

    public TaskListForm(TaskDto taskDto) {
        this.id = taskDto.getId();
        this.name = taskDto.getName();
        this.content = taskDto.getContent();
        this.status = String.valueOf(taskDto.getStatus());
        this.dueDate = formatDatetime(taskDto.getDueDate());
    }

    private String formatDatetime(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
