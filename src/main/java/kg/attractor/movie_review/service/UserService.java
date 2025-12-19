package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getList();

    UserDto getUserById(int id);

    Integer createUser(UserDto userDto);

    List<UserDto> searchByName(String name);
}
