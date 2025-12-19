package kg.attractor.movie_review.dao;

import kg.attractor.movie_review.model.MovieImage;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ImageDao {
    private final JdbcTemplate jdbcTemplate;


    public Optional<MovieImage> findById(Long id) {
        String sql = "select * from movie_images where id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(
                                sql,
                                new BeanPropertyRowMapper<>(MovieImage.class),
                                id
                        )
                )
        );
    }

    public void save(MovieImage image) {
        String sql = "insert into movie_images (movie_id, file_name) values (?, ?)";
        jdbcTemplate.update(sql, image.getMovieId(), image.getFileName());
    }
}
