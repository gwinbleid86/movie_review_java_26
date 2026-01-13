package kg.attractor.movie_review.exceptions;

import java.util.NoSuchElementException;

public class RoleNotFoundException extends NoSuchElementException {
    public RoleNotFoundException() {
        super("Role not found");
    }
}
