package kg.attractor.movie_review.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cast {
    private String fullName;
    private String role;

    @Override
    public String toString() {
        String format = String.format("%s в роли %s", fullName, role);
        return format;
    }
}
