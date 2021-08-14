package nguyenhuuvu.utils;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class UserUtil {
    public static String createUsername(String fullname)
    {
        fullname = fullname.replaceAll("[áạảãàâấậẩẫầăắặẵẳ]", "a");
        fullname = fullname.replaceAll("[êếễểệẽẻẹéè]", "e");
        fullname = fullname.replaceAll("[ìỉĩịí]", "i");
        fullname = fullname.replaceAll("[ỏòõọóôốồỗộốơớỡợở]", "o");
        fullname = fullname.replaceAll("[ủụũúùưứừựữ]", "u");
        fullname = fullname.replaceAll("[ỳýỵỹỷ]", "y");
        fullname = fullname.replaceAll("[đ]", "d");
        fullname = fullname.replaceAll("[|\\/()*~ ]", "");
        fullname = fullname.toLowerCase();
        fullname += new Random().nextInt(1000000) + 1;
        return fullname;
    }

    public static String generateToken()
    {
        return UUID.randomUUID().toString();
    }
    public static Integer generateCode()
    {
        return new Random().nextInt(890000) + 100000;
    }
}
