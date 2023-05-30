package hello.project.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member extends AuditableEntity {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id")
    private Household household;

    private String email;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Timezone timezone;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Task> createdTaskList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<TaskMember> taskList = new ArrayList<>();

    protected Member() {}

    public Member(String email, String username, String password, Timezone timezone) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.timezone = timezone;
    }

    public void updateMember(String username, Timezone timezone) {
        this.username = username;
        this.timezone = timezone;
    }

    // :: 연관관계 바인딩 :: //
    public void changeHousehold(Household household) {
        this.household = household;
        household.getMembers().add(this);
    }
}
