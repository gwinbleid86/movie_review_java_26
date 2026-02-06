package kg.attractor.movie_review.service.impl;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.movie_review.common.Utilities;
import kg.attractor.movie_review.dao.RoleDao;
import kg.attractor.movie_review.dao.UserDao;
import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exceptions.RoleNotFoundException;
import kg.attractor.movie_review.exceptions.UserNotFoundException;
import kg.attractor.movie_review.model.Role;
import kg.attractor.movie_review.model.User;
import kg.attractor.movie_review.repository.RoleRepository;
import kg.attractor.movie_review.repository.UserRepository;
import kg.attractor.movie_review.service.EmailService;
import kg.attractor.movie_review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final RoleRepository roleRepository;

    @Override
    public User findByUsername(String username) {
        User user = userDao.searchByName(username)
                .orElseThrow(UserNotFoundException::new);
        List<Role> roles = roleDao.findByUserEmail(user.getEmail());

        user.setRoles(roles);
        return user;
    }

    @Override
    public List<UserDto> getList() {
        List<User> users = userDao.getUsers();
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> {
            UserDto userDto = new UserDto();
            userDto.setEmail(user.getEmail());
            userDto.setName(user.getUsername());
            userDto.setPassword(user.getPassword());
            userDtos.add(userDto);
        });
        return userDtos;
    }

//    @Override
//    public UserDto getUserById(int id) {
//        User user = userDao.getUserById(id)
//                .orElseThrow(UserNotFoundException::new);
//        UserDto userDto = new UserDto();
//        userDto.setEmail(user.getEmail());
//        userDto.setName(user.getUsername());
//        userDto.setPassword(user.getPassword());
//        return userDto;
//    }

    @Override
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(Boolean.TRUE);

        Role role = roleRepository.findByRoleName("GUEST")
                .orElseThrow(RoleNotFoundException::new);
        user.setRoles(List.of(role));

        user = userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public User save(User user) {
        userDao.create(user);
        return findByUsername(user.getUsername());
    }

    @Override
    public User create(User user) {
        if (userDao.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }
        return save(user);
    }

    @Override
    public UserDetailsService getUserDetailsService() {
        return this::findByUsername;
    }

    @Override
    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByUsername(username);
    }

    @Override
    public void getAdmin() {
        var user = getCurrentUser();
        var role = roleDao.findByRoleName("ADMIN")
                .orElseThrow(RoleNotFoundException::new);
        user.setRoles(List.of(role));
        save(user);
    }

    @Override
    public UserDto searchByEmail(String email) {
        User user = userDao.searchByName(email).orElseThrow(UserNotFoundException::new);
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getUsername());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    @Override
    public void makeResetPasswordLink(HttpServletRequest request) throws UsernameNotFoundException, MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateResetPasswordToken(token, email);
        String resetPasswordLink = Utilities.getSiteUrl(request) + "/auth/reset_password?token=" + token;
        emailService.sendEmail(email, resetPasswordLink);
    }

    private void updateResetPasswordToken(String token, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        user.setResetPasswordToken(token);
        userRepository.saveAndFlush(user);
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.saveAndFlush(user);
    }
}
