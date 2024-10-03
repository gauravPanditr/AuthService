package org.example.utilies;

public class PasswordValidator implements ValidationUtilies<String> {
    @Override
    public boolean isValid(String password) {
        return password != null && password.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");
    }
}
