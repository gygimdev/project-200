package hello.project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseholdApply extends AuditableEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="household_apply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id")
    private Household household;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    @Column(columnDefinition = "BOOLEAN")
    private Boolean isApproved = false;

    @Builder
    public HouseholdApply(Household household, Member member) {
        this.household = household;
        this.member = member;
        this.isApproved = false;
    }
}
