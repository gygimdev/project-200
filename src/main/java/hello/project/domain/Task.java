package hello.project.domain;

import hello.project.dto.task.TaskDto;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Task extends AuditableEntity {
    protected Task() {};

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="task_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id")
    private Household household;

    private String name;

    private String content;

    private LocalDateTime dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.TODO;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskMember> taskMemberList;

    public Task(String name, String content, LocalDateTime dueDate, Member member, Household household) {
        this.name = name;
        this.content = content;
        this.dueDate = dueDate;
        this.member = member;
        this.household = household;

        member.getCreatedTaskList().add(this);
        household.getTasks().add(this);
    }

    public Task updateTask(TaskDto dto) {
        this.name = dto.getName();
        this.content = dto.getContent();
        this.dueDate = dto.getDueDate();
        this.status = dto.getStatus();
        return this;
    }
}
