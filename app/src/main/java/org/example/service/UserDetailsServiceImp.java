package org.example.service;


import lombok.AllArgsConstructor;
import lombok.Data;

import org.example.enities.UserInfo;
import org.example.model.UserInfoDto;
import org.example.repositary.UserRepositary;
import org.example.utilies.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
@Data
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private final UserRepositary userRepositary;

    @Autowired
    private final PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo user=userRepositary.findByUsername(username);
        if(user==null){
            throw  new RuntimeException("Username Not Found");
        }
    return  new CustomerUserDetails(user);

    }
    private UserInfo checkUserExist(UserInfoDto userInfoDto){
         return userRepositary.findByUsername(userInfoDto.getUsername());
    }

    public String SignUpUser(UserInfoDto userInfoDto){
        FirstNameValidator firstNameValidator = new FirstNameValidator();
        if (!firstNameValidator.isValid(userInfoDto.getFirstName())) {
            return "Invalid first name";
        }


        LastNameValidator lastNameValidator = new LastNameValidator();
        if (!lastNameValidator.isValid(userInfoDto.getLastName())) {
            return "Invalid last name";
        }


        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        if (!phoneNumberValidator.isValid(userInfoDto.getPhoneNumber())) {
            return "Invalid phone number";
        }

        // Validate email
        EmailValidator emailValidator = new EmailValidator();
        if (!emailValidator.isValid(userInfoDto.getEmail())) {
            return "Invalid email address";
        }

        // Validate password
        PasswordValidator passwordValidator = new PasswordValidator();
        if (!passwordValidator.isValid(userInfoDto.getPassword())) {
            return "Invalid password";
        }

        UsernameValidator usernameValidator=new UsernameValidator();
        if(!usernameValidator.isValid(userInfoDto.getUsername())){
            return "Inavlid Username";
        }

        userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
        if(Objects.nonNull(checkUserExist(userInfoDto))){
            return null;
        }
        String userId= UUID.randomUUID().toString();
        userRepositary.save(new UserInfo(userId,userInfoDto.getUsername(),userInfoDto.getPassword(),new HashSet<>()));
      return  userId;
    }

    public String getUserByUsername(String userName){

        return Optional.of(userRepositary.findByUsername(userName)).map(UserInfo::getUserId).orElse(null);
    }


}
