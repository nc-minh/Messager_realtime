package nguyenhuuvu.exception;

public class AccountHandleException extends RuntimeException{
    private AccountHandleException() {
    }

    public AccountHandleException(String message) {
        super(message);
    }
}
