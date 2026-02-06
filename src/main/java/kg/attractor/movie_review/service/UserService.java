package kg.attractor.movie_review.service;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {
    User findByUsername(String username);

    List<UserDto> getList();

    User createUser(UserDto userDto);

    User save(User user);

    User create(User user);

    UserDetailsService getUserDetailsService();

    User getCurrentUser();

    void getAdmin();

    UserDto searchByEmail(String email);

    void makeResetPasswordLink(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException;

    User getByResetPasswordToken(String token);

    void updatePassword(User user, String newPassword);
}
