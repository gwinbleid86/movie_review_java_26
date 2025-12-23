package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dao.UserDao;
import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exceptions.UserNotFoundException;
import kg.attractor.movie_review.model.User;
import kg.attractor.movie_review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

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
    public void createUser(UserDto userDto) {
        userDao.create(userDto);
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
