package hello.project.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberForm {
    @NotEmpty
    private String email;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
