package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({AlreadyBookedException.class})
    public ResponseEntity<?> handleAlreadyBookedException(RuntimeException ex){

        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({TicketNotFoundException.class})
    public ResponseEntity<?> handleTicketNotFoundException(RuntimeException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({WrongPasswordException.class})
    public ResponseEntity<?> HandleWrongPassword(RuntimeException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }


}
