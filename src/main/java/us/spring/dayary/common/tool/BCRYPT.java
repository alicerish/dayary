package us.spring.dayary.common.tool;

import org.mindrot.jbcrypt.BCrypt;

public class BCRYPT {

    public static String hashpw(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkpw(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
