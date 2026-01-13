package kg.attractor.movie_review.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role {
    private Long id;
    private String roleName;
    private Long authorityId;
}
