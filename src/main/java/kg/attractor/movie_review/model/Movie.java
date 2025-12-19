package kg.attractor.movie_review.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private int id;
    private String name;
    private int releaseYear;
    private String description;
    private int directorId;

    @Override
    public String toString() {
        return String.format("Фильм: <<%s>>,%nГод выпуска: %s,%nОписание: %s,%n%s", name, releaseYear, description, directorId);
    }
}
