package kg.attractor.movie_review.exceptions;

import java.util.NoSuchElementException;

public class ImageNotFoundException extends NoSuchElementException {
    public ImageNotFoundException() {
        super("Image not found");
    }
}
