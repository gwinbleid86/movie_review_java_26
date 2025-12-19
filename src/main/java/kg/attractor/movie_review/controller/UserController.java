package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getUserList(){
        return userService.getList();
    }

    @GetMapping("{id}")
    public UserDto getUserById(
            @PathVariable int id
    ) {
        return userService.getUserById(id);
    }

    @PostMapping
    public Integer createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping("search")
    public List<UserDto> searchUsers(
            @RequestParam(name = "name") String name
    ) {
        return userService.searchByName(name);
    }
}
