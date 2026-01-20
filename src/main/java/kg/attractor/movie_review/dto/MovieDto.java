package kg.attractor.movie_review.dto;

import kg.attractor.movie_review.model.Cast;
import kg.attractor.movie_review.model.Director;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private int id;
    private String name;
    private Director director;
    private int year;
    private List<Cast> castList;
    private String description;
}
