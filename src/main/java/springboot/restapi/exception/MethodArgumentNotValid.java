package springboot.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.CONFLICT)
public class MethodArgumentNotValid extends Exception {
    public MethodArgumentNotValid(String message) {
        super(message);
    }
}

