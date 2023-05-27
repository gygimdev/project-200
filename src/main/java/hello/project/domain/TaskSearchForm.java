package hello.project.domain;

import hello.project.dto.task.TaskDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskSearchForm {

    private String start;

    private String end;

    private String keyword;

    private List<TaskDto> dtos = new ArrayList<>();
}
