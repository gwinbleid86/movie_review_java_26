package kg.attractor.movie_review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CastDto {
    private Long id;
    private String fullName;
    private String role;
}
