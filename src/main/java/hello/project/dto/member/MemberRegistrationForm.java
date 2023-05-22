package hello.project.dto.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberRegistrationForm {
    @NotEmpty
    private String email;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
