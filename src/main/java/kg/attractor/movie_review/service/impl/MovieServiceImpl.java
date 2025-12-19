package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.model.Cast;
import kg.attractor.movie_review.model.Director;
import kg.attractor.movie_review.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Override
    public MovieDto getMovies() {
        MovieDto movieDto = new MovieDto();
        movieDto.setName("Good omens");
        movieDto.setYear(2019);
        movieDto.setDescription("TV Series");
        movieDto.setDirector(new Director("Douglas Mackinnon"));
        movieDto.setCastList(List.of(
                new Cast("Michael Sheen", "Aziraphale"),
                new Cast("David Tennant", "Crowley")));
        return movieDto;
    }
}
