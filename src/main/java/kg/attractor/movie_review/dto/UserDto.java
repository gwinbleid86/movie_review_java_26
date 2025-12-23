package kg.attractor.movie_review.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank
    @Email
    private String email;
    private String name;

    @NotBlank
    @Size(
            min = 5, max = 24,
            message = "Length must be greater/equals than 5 and less/equals than 24 digits"
    )
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Should contain at least one UPPERCASE letter, one number"
    )
    private String password;
}
