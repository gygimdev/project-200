package hello.project.dto.member;

import hello.project.domain.Timezone;
import hello.project.validator.UniqueEmail;
import hello.project.validator.ValidEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberRegistrationForm {

    @NotEmpty
    @UniqueEmail
    private String email;

    @NotEmpty
    @Size(min = 2, max = 25)
    private String username;

    @NotEmpty
    @Size(min = 6, max = 25)
    private String password;

    @ValidEnum(enumClass = Timezone.class)
    private Timezone timezone;
}
