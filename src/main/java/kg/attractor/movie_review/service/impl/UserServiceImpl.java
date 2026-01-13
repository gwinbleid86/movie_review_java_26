package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dao.RoleDao;
import kg.attractor.movie_review.dao.UserDao;
import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exceptions.RoleNotFoundException;
import kg.attractor.movie_review.exceptions.UserNotFoundException;
import kg.attractor.movie_review.model.Role;
import kg.attractor.movie_review.model.User;
import kg.attractor.movie_review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

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
        user.setUsername(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        userDao.create(userDto);
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
}
