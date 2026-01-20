package kg.attractor.movie_review.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.movie_review.service.impl.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final ErrorService errorService;

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<CustomErrorResponse> noSuchElementHandler(NoSuchElementException e) {
//        return new ResponseEntity<>(errorService.makeResponse(e), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<CustomErrorResponse> validationHandler(MethodArgumentNotValidException e) {
//        return new ResponseEntity<>(errorService.makeResponse(e.getBindingResult()), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound(Model model, HttpServletRequest request) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/error";
    }
}
