package nguyenhuuvu.exception;

import nguyenhuuvu.model.MyException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandleException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("errorCode", "devchat001");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<?> duplicateEmailException(DuplicateEmailException ex) {
        MyException myException = new MyException("devchat002", "Email đang liên kết với một tài khoản khác", 400);
        return new ResponseEntity<>(myException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalException(Exception ex) {
        ex.printStackTrace();
        MyException myException = new MyException("devchat000", ex.getMessage(), 400);
        return new ResponseEntity<>(myException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericUsernameException.class)
    public ResponseEntity<?> genericUsernameException(GenericUsernameException ex) {

        MyException myException = new MyException("devchat004", "Lỗi hiếm =)) render cho client submit lại là đc", 400);
        return new ResponseEntity<>(myException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        MyException myException = new MyException("devchat005", ex.getMessage(), 400);
        return new ResponseEntity<>(myException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountHandleException.class)
    public ResponseEntity<?> accountNotFoundException(AccountHandleException ex) {
        MyException myException = new MyException("devchat006", ex.getMessage(), 400);
        return new ResponseEntity<>(myException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JwtTokenException.class)
    public ResponseEntity<?> accountNotFoundException(JwtTokenException ex) {
        MyException myException = new MyException("devchat007", ex.getMessage(), 400);
        return new ResponseEntity<>(myException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ErrorMessage> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ErrorMessage errorDetails = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<?> badCredentialsException(BadCredentialsException ex) {
        MyException myException = new MyException("devchat009", "Incorrect account or password", 400);
        return new ResponseEntity<>(myException, HttpStatus.BAD_REQUEST);
    }
}
