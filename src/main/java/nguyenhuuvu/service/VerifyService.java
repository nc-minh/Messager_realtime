package nguyenhuuvu.service;

public interface VerifyService {
    boolean verifyToken(String token);

    boolean verifyCode(String email, String code);
}
