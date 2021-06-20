package nguyenhuuvu.utils;

import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
    public static Date calculateExpiryDate(int expiryTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expiryTime);
        return calendar.getTime();
    }

    public static boolean checkTokenExpire(Date date) {
        Date dateNow = new Date();
        if (date.getTime() > dateNow.getTime())
            return true;
        return false;
    }
}
