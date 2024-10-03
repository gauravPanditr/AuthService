package org.example.utilies;


public class FirstNameValidator implements ValidationUtilies<String>{

    @Override
    public boolean isValid(String firstName) {
        return firstName != null && !firstName.trim().isEmpty() && firstName.matches("^[A-Z][a-z]*$");
    }
}
