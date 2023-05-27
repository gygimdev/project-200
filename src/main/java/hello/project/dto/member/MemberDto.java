package hello.project.dto.member;

import hello.project.domain.Language;
import hello.project.domain.Timezone;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberDto {

    private Long id;

    private String email;

    private String username;

    private Timezone timezone;
}
