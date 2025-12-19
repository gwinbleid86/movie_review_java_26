package kg.attractor.movie_review.dto;

import kg.attractor.movie_review.model.Cast;
import kg.attractor.movie_review.model.Director;
import lombok.Data;

import java.util.List;

@Data
public class MovieDto {
//    private int id;
    private String name;
    private Director director;
    private int year;
    private List<Cast> castList;
    private String description;
}
