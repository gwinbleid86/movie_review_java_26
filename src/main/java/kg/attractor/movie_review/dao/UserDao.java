package kg.attractor.movie_review.dao;

import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final KeyHolder keyHolder = new GeneratedKeyHolder();


    public List<User> getUsers() {
        String sql = "SELECT * FROM users";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), id)
                )
        );
    }

    public Integer create(UserDto userDto) {
        String sql = "insert into users(name, password) values(?, ?)";

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userDto.getName());
            ps.setString(2, userDto.getPassword());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public List<User> searchByName(String name) {
        String sql = "SELECT * FROM users WHERE name like :name";

        return namedParameterJdbcTemplate.query(
                sql,
                new MapSqlParameterSource()
                        .addValue("name", "%" + name + "%"), // %Ale%
                new BeanPropertyRowMapper<>(User.class)
        );
    }
}
