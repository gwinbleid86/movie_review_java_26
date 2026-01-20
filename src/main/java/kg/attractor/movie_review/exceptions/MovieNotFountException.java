package kg.attractor.movie_review.exceptions;

import java.util.NoSuchElementException;

public class MovieNotFountException extends NoSuchElementException {
    public MovieNotFountException() {
        super("Movie not found!");
    }
}
