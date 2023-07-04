package hello.project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Entity
@Builder
public class HouseholdInvitation extends AuditableEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="household_invitation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id")
    private Household household;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    @Builder.Default
    @Column(columnDefinition = "BOOLEAN")
    private Boolean isApproved = false;
}
