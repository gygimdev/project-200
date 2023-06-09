package hello.project.dto.member;

import hello.project.domain.Household;
import hello.project.domain.Language;
import hello.project.domain.Member;
import hello.project.domain.Timezone;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDto {

    private Long id;

    private String email;

    private String username;

    private Timezone timezone;
}
