package nguyenhuuvu.utils;

public class Constant {
    public static final String VERIFY_MAIL_TEMPLATE = "verify-template";
    public static final String VERIFY_ACCOUNT_SUBJECT = "Xác nhận tài khoản DevChat";
    public static final String VERIFY_ACCOUNT_TIME_EXPIRE = "24 giờ";
    public static final String RESET_PASSWORD_TIME_EXPIRE = "2 giờ";

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    /**
     * one day
     */
    public static final int TIME_VERIFY_SIGNUP = 60*24*30;

    /**
     * one hour
     */
    public static final int TIME_VERIFY_FORGOT_PASSWORD = 1*24;
}
