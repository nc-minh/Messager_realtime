package nguyenhuuvu.exception;

import lombok.Data;

public class JwtTokenException extends RuntimeException{
    public JwtTokenException(String message) {
        super(message);
    }
}
