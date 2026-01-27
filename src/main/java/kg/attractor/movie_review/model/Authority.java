package kg.attractor.movie_review.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "AUTHORITIES")
public class Authority {
    @Id
    private Long id;

    @Column(name = "AUTHORITY")
    private String authorityName;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "roles_authorities",
//            joinColumns = @JoinColumn(name = "authority_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
    private List<Role> roles;
}
