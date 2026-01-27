package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.MovieDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {
    Page<MovieDto> getMovies(Pageable page);

    MovieDto findById(Long id);
}
