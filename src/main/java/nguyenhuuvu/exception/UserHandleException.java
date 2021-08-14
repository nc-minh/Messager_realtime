package nguyenhuuvu.exception;

public class UserHandleException extends RuntimeException{
    private UserHandleException() {
    }

    public UserHandleException(String message) {
        super(message);
    }
}
