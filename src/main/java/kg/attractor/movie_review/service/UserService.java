package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getList();

    void createUser(UserDto userDto);

    UserDto searchByEmail(String email);
}
