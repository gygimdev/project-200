package hello.project.dto.household;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class HouseholdApplyForm {
    private Long householdApplyId;

    private String householdName;

    private Boolean isApproved;

    private LocalDateTime createdAt;
}
