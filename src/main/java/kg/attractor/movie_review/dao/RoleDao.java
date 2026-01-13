package kg.attractor.movie_review.dao;

import kg.attractor.movie_review.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleDao {
    private final JdbcTemplate jdbcTemplate;


    public List<Role> findByUserEmail(String email) {
        String sql = "select r.* " +
                "from roles r, user_table u " +
                "where u.email = ? " +
                "and u.role_id = r.id;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Role.class), email);
    }

    public Optional<Role> findByRoleName(String admin) {
        String sql = "select * from roles where role = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Role.class), admin)
                )
        );
    }
}
