package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dao.MovieDao;
import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.exceptions.MovieNotFountException;
import kg.attractor.movie_review.model.Movie;
import kg.attractor.movie_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    @Override
    public List<MovieDto> getMovies() {
        List<Movie> movies = movieDao.getAll();
        List<MovieDto> moviesDto = new ArrayList<>();
        movies.forEach(movie -> {
            MovieDto movieDto = new MovieDto();
            movieDto.setId(movie.getId());
            movieDto.setName(movie.getName());
            movieDto.setYear(movie.getReleaseYear());
            movieDto.setDescription(movie.getDescription());
            moviesDto.add(movieDto);
        });

        return moviesDto;
    }

    @Override
    public MovieDto findById(Long id) {
        Movie movie = movieDao.findById(id)
                .orElseThrow(MovieNotFountException::new);
        return MovieDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .year(movie.getReleaseYear())
                .description(movie.getDescription())
                .build();
    }
}
