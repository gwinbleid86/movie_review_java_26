package kg.attractor.movie_review.exceptions;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {
    public UserNotFoundException() {
        super("User not found!");
    }
}
