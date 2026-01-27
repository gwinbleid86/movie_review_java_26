package kg.attractor.movie_review.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String index(Model model, Authentication auth) {
        if (auth != null) {
            var authorities = auth.getAuthorities();
            model.addAttribute("authorities", authorities);
        }
        model.addAttribute("world", "Java");

        return "index";
    }
}
