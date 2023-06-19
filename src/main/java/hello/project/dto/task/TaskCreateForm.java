//package hello.project.dto.task;
//
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import lombok.Data;
//
//import java.time.LocalDateTime;
//
//@Data
//public class TaskCreateForm {
//
//    @NotEmpty
//    private String name;
//
//    private String content;
//
//    @NotNull
//    private LocalDateTime dueDate;
//
//    public TaskCreateForm() {}
//
//    public TaskCreateForm(String name, String content, LocalDateTime dueDate)  {
//        this.name = name;
//        this.content = content;
//        this.dueDate = dueDate;
//    }
//}
