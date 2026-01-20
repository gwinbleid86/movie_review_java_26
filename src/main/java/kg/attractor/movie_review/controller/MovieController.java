package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public String getMovies(Model model) {
        model.addAttribute("movies", movieService.getMovies());
        return "movies/index";
    }

    @GetMapping("{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("movie", movieService.findById(id));
        return "movies/movie";
    }
}
