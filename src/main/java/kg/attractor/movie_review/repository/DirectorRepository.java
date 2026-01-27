package kg.attractor.movie_review.repository;

import kg.attractor.movie_review.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

    //    @Query(
//            value = "select d from Director as d " +
//                    "where d.id = (select m.director.id from Movie as m where m.id = :movieId)"
//    )
    @Query(
            value = "select * from DIRECTOR " +
                    "where id = (select director_id from MOVIE where ID = :movieId);",
            nativeQuery = true
    )
    Optional<Director> findByMovieId(Long movieId);

}
