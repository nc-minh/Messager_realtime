package nguyenhuuvu.utils;

import java.util.Random;
import java.util.UUID;

public class AccountUtil {
    public static String createUsername(String fisrtname, String lastname)
    {
        String result = fisrtname + lastname;
        result = result.replaceAll("[áạảãàâấậẩẫầăắặẵẳ]", "a");
        result = result.replaceAll("[êếễểệẽẻẹéè]", "e");
        result = result.replaceAll("[ìỉĩịí]", "i");
        result = result.replaceAll("[ỏòõọóôốồỗộốơớỡợở]", "o");
        result = result.replaceAll("[ủụũúùưứừựữ]", "u");
        result = result.replaceAll("[ỳýỵỹỷ]", "y");
        result = result.replaceAll("[đ]", "d");
        result = result.replaceAll("[|\\/()*~ ]", "");
        return result;
    }

    public static void main(String[] args) {
        System.out.println(generateToken());
    }

    public static String generateToken()
    {
        return UUID.randomUUID().toString();
    }
}
