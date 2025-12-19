package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // http://localhost:8080
@RequestMapping("movies") // http://localhost:8080/movies
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    //    @GetMapping("{id}") // http://localhost:8089/movies/{id}
    @GetMapping
    public ResponseEntity<MovieDto> getMovies() {
        return ResponseEntity.ok(movieService.getMovies());
    }

    @PostMapping
    public HttpStatus createMovie() {
        return HttpStatus.CREATED;
    }

//    @PostMapping
//    @PutMapping
//    @DeleteMapping
}
