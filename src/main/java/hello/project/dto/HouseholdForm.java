package hello.project.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class HouseholdForm {

    @NotEmpty
    private String name;
}
