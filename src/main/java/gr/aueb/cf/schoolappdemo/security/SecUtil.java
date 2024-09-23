package gr.aueb.cf.schoolappdemo.security;

import org.mindrot.jbcrypt.BCrypt;

public class SecUtil {

    private SecUtil() {}

    //blowfish
    public static String encryptPassword(String inputPassword) {
        //delaying = 12 rounds
        int workload = 12;

        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(inputPassword, salt);
    }

    public static boolean checkPassword(String inputPassword, String encryptedPassword) {
        return BCrypt.checkpw(inputPassword, encryptedPassword);
    }
}
