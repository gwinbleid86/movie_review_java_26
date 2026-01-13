package kg.attractor.movie_review.dao;

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

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final KeyHolder keyHolder = new GeneratedKeyHolder();


    public List<User> getUsers() {
        String sql = "SELECT * FROM USER_TABLE";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public void create(User user) {
        String sql = "insert into USER_TABLE(email, USERNAME, password) values(?,?,?)";

        jdbcTemplate.update(sql, user.getEmail(), user.getUsername(), user.getPassword());
    }

    public Optional<User> searchByName(String email) {
        String sql = "SELECT * FROM USER_TABLE WHERE EMAIL like :email";

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        namedParameterJdbcTemplate.query(
                                sql,
                                new MapSqlParameterSource()
                                        .addValue("email", "%" + email + "%"), // %Ale%
                                new BeanPropertyRowMapper<>(User.class)
                        )
                )
        );
    }

    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM USER_TABLE WHERE email = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username)
                )
        );
    }

    public boolean existsByUsername(String username) {
        String sql = "select count(EMAIL) from USER_TABLE where USERNAME = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }
}
