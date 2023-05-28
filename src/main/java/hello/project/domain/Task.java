package hello.project.domain;

import hello.project.dto.task.TaskDto;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
    private Member member; //테스크 생성자

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.TODO;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskMember> taskMemberList;

    /** Task 생성
     * @param dto        : TaskDto
     * @param loginMember: 로그인맴버
     * @param household  : Household(가정)
     */
    public Task(TaskDto dto, Member loginMember, Household household) {
        this.name = dto.getName();
        this.content = dto.getContent();
        this.dueDate = createUtcTime(dto, loginMember);
        this.member =  loginMember;
        this.household = household;

        member.getCreatedTaskList().add(this);
        household.getTasks().add(this);
    }

    /** Task 업데이트
     * @param dto TaskDto
     * @param loginMember 로그인맴버
     * @return
     */
    public Task updateTask(TaskDto dto, Member loginMember) {
        this.name = dto.getName();
        this.content = dto.getContent();
        this.dueDate = createUtcTime(dto, loginMember);
        this.status = dto.getStatus();
        return this;
    }

    /** UTC 로변환
     * 사용자입력 dueDate 값을 DB 에 저장하기 위해 UTC 로 바꾼다.
     * @param dto TaskDto
     * @param member Member
     * @return LocalDateTime
     */
    private LocalDateTime createUtcTime(TaskDto dto, Member member) {
        String code = member.getTimezone().getTimeCode();
        ZoneId zoneId = ZoneId.of(code);
        LocalDateTime localDateTime = dto.getDueDate();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId).withZoneSameInstant(ZoneOffset.UTC);
        return zonedDateTime.toLocalDateTime();
    }

}
