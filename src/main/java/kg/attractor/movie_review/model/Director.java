package kg.attractor.movie_review.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Director {
    private int id;
    private String fullName;

    @Override
    public String toString() {
        return String.format("Режиссер: %s", fullName);
    }
}
