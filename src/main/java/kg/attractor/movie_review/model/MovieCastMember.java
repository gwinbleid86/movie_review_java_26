package kg.attractor.movie_review.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MOVIE_CAST_MEMBER")
public class MovieCastMember {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MovieCastMemberCompositeId implements Serializable {
        @ManyToOne
        @JoinColumn(name = "movie_id")
        private Movie movie;

        @ManyToOne
        @JoinColumn(name = "cast_member_id")
        private Cast cast;
    }

    @EmbeddedId
    private MovieCastMemberCompositeId id;

    private String role;
}
