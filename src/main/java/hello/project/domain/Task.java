package hello.project.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Fetch;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.NEW;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskMember> taskMemberList;

    public Task(String name, String content, Member member, Household household) {
        this.name = name;
        this.content = content;
        this.member = member;
        this.household = household;

        member.getCreatedTaskList().add(this);
        household.getTasks().add(this);
    }
}
