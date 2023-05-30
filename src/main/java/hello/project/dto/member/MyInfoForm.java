package hello.project.dto.member;

import hello.project.domain.Timezone;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyInfoForm {

    private Long id;
    private String email;
    private String username;
    private Timezone timezone;
    private String householdName;
}
