package hello.project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends AuditableEntity {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id")
    private Household household;

    @NotNull
    @Size(min = 3, max = 254)
    private String email;

    @NotNull
    @Size(min = 2, max = 25)
    private String username;

    @NotNull
    @Size(min = 6)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Timezone timezone;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Task> createdTaskList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<TaskMember> taskList = new ArrayList<>();

    protected Member() {}

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
