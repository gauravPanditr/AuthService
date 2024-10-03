package org.example.utilies;

public class PhoneNumberValidator implements ValidationUtilies<Long>{
    @Override
    public boolean isValid(Long phoneNumber) {
        return phoneNumber != null && String.valueOf(phoneNumber).matches("^\\d{10}$");
    }
}
