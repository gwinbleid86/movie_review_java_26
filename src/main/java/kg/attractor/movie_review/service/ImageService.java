package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.MovieImageDto;
import org.springframework.http.ResponseEntity;

public interface ImageService {
    ResponseEntity<?> findById(Long id);

    void save(MovieImageDto movieImageDto);
}
