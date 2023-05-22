package hello.project.dto.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberDto {

    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String username;
}
