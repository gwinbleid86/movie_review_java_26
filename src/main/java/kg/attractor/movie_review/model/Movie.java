package kg.attractor.movie_review.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MOVIE")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(name = "RELEASE_YEAR")
    private int releaseYear;
    private String description;

    @ManyToOne
    @JoinColumn(name = "DIRECTOR_ID", nullable = false)
    private Director director;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Set<MovieCastMember> movieCastMemberSet = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "MOVIE_ID")
    private Collection<MovieImage> images;
}
