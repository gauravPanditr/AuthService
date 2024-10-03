package org.example.utilies;

public class LastNameValidator implements ValidationUtilies<String>{
    @Override
    public boolean isValid(String lastName) {
        return lastName != null && !lastName.trim().isEmpty() && lastName.matches("^[A-Z][a-z]*$");
    }
}
