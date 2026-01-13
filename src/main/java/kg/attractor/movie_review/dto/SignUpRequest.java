package kg.attractor.movie_review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {
    @Size(min = 5, max = 50, message = "Username must includes 5 to 50 letters")
    @NotBlank
    private String username;

    @Size(min = 5, max = 255, message = "Email must include 5 to 255 letters")
    @NotBlank
    private String email;

    @Size(min = 3, max = 255)
    @NotBlank
    private String password;
}
