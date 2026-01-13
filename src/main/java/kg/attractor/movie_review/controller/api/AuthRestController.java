package kg.attractor.movie_review.controller.api;

import kg.attractor.movie_review.dto.JwtAuthResponse;
import kg.attractor.movie_review.dto.SignInRequest;
import kg.attractor.movie_review.dto.SignUpRequest;
import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.model.User;
import kg.attractor.movie_review.service.UserService;
import kg.attractor.movie_review.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping("username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PostMapping
    public User registerUser(@RequestBody UserDto user) {
        return userService.createUser(user);
    }

    @PostMapping("signUp")
    public JwtAuthResponse signUp(@RequestBody SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("signIn")
    public JwtAuthResponse signIn(@RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
