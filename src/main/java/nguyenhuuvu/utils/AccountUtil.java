package nguyenhuuvu.utils;

import java.util.Random;
import java.util.UUID;

public class AccountUtil {
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
        fullname += new Random().nextInt(1000000) + 1;
        return fullname;
    }

    public static void main(String[] args) {
        System.out.println(generateToken());
    }

    public static String generateToken()
    {
        return UUID.randomUUID().toString();
    }
    public static int generateCode()
    {
        return new Random().nextInt(890000) + 100000;
    }
}
