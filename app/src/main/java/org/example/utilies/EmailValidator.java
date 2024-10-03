package org.example.utilies;

public class EmailValidator implements ValidationUtilies<String> {
    @Override
    public boolean isValid(String email) {
        return email != null && email.matches("^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,4}$");
    }
}
