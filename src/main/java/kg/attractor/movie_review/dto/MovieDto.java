package kg.attractor.movie_review.dto;

import kg.attractor.movie_review.model.Movie;
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
    private DirectorDto director;
    private int year;
    private List<CastDto> castList;
    private String description;

    public static MovieDto convertToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setName(movie.getName());
        movieDto.setYear(movie.getReleaseYear());
        movieDto.setDescription(movie.getDescription());
        return movieDto;
    }
}
