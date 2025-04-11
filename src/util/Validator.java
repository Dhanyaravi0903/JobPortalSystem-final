package util;

import java.util.regex.*;

public class Validator {

    public static boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    
    public static boolean validatePassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    
    public static boolean validateID(String idProof) {
        if (idProof == null || idProof.isEmpty()) {
            return false; 
        }
        String regex = "^[0-9]{12}$";  
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(idProof);
        return matcher.matches();
    }
}
