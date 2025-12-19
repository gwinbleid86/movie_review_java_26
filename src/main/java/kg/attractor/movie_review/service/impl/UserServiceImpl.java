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
import java.util.NoSuchElementException;

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
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setPassword(user.getPassword());
            userDtos.add(userDto);
        });
        return userDtos;
    }

    @Override
    public UserDto getUserById(int id) {
        User user = userDao.getUserById(id)
                .orElseThrow(UserNotFoundException::new);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    @Override
    public Integer createUser(UserDto userDto) {
        return userDao.create(userDto);
    }

    @Override
    public List<UserDto> searchByName(String name) {
        List<User> users = userDao.searchByName(name);
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setPassword(user.getPassword());
            userDtos.add(userDto);
        });
        return userDtos;
    }
}
