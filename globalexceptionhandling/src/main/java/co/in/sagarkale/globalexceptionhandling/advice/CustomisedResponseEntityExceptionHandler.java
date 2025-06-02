package co.in.sagarkale.globalexceptionhandling.advice;

import co.in.sagarkale.globalexceptionhandling.dto.APIErroResponseDTO;
import co.in.sagarkale.globalexceptionhandling.exceptions.UserInActiveException;
import co.in.sagarkale.globalexceptionhandling.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<APIErroResponseDTO> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
        System.out.println("handleAllExceptions");
        APIErroResponseDTO apiErroResponseDTO = new APIErroResponseDTO(
                false,
                "Something went wrong",
                null
        );
        return new ResponseEntity<>(apiErroResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<APIErroResponseDTO> handleUserNotFoundExceptions(Exception ex, WebRequest request) throws Exception{
        System.out.println("handleUserNotFoundExceptions");
        APIErroResponseDTO apiErroResponseDTO = new APIErroResponseDTO(
                false,
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(apiErroResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserInActiveException.class)
    public final ResponseEntity<APIErroResponseDTO> handleUserInActiveExceptions(Exception ex, WebRequest request) throws Exception {
        APIErroResponseDTO apiErroResponseDTO = new APIErroResponseDTO(
                false,
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(apiErroResponseDTO, HttpStatus.FORBIDDEN);
    }

}
