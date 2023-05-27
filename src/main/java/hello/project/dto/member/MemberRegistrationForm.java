package hello.project.dto.member;

import hello.project.domain.Language;
import hello.project.domain.Timezone;
import hello.project.validator.ValidEnum;
import jakarta.validation.constraints.NotBlank;
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

    @ValidEnum(enumClass = Timezone.class)
    private Timezone timezone;
}
