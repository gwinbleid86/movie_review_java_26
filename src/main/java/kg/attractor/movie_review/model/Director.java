package kg.attractor.movie_review.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DIRECTOR")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FULLNAME", nullable = false)
    private String fullName;

    @OneToMany
    @JoinColumn(name = "DIRECTOR_ID")
    private Set<Movie> movies = new HashSet<>();
}
