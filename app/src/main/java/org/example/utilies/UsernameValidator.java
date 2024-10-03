package org.example.utilies;

public class UsernameValidator implements ValidationUtilies<String>{
    @Override
    public boolean isValid(String username) {
        return username != null && !username.trim().isEmpty() && username.matches("^[a-zA-Z0-9_]{5,20}$");
    }
}
