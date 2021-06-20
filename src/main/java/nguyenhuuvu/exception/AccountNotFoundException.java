package nguyenhuuvu.exception;

public class AccountNotFoundException extends RuntimeException{
    private AccountNotFoundException() {
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}
