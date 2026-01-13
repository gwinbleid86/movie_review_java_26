package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dao.RoleDao;
import kg.attractor.movie_review.dto.JwtAuthResponse;
import kg.attractor.movie_review.dto.SignInRequest;
import kg.attractor.movie_review.dto.SignUpRequest;
import kg.attractor.movie_review.exceptions.RoleNotFoundException;
import kg.attractor.movie_review.model.User;
import kg.attractor.movie_review.service.JwtService;
import kg.attractor.movie_review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleDao roleDao;

    public JwtAuthResponse signUp(SignUpRequest request) {
        var role = roleDao.findByRoleName("USER")
                .orElseThrow(RoleNotFoundException::new);

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(List.of(role));

        var jwt = jwtService.generateToken(user);

        return new JwtAuthResponse(jwt);
    }

    public JwtAuthResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userService.getUserDetailsService().loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthResponse(jwt);
    }
}
