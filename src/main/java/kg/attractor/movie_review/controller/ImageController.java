package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.dto.MovieImageDto;
import kg.attractor.movie_review.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("{id}")
    public ResponseEntity<?> findImageById(@PathVariable("id") Long id) {
        return imageService.findById(id);
    }

    @PostMapping
    public HttpStatus saveImage(MovieImageDto movieImageDto) {
        imageService.save(movieImageDto);
        return HttpStatus.CREATED;
    }
}
