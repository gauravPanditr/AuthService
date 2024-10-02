package org.example.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.enities.UserInfo;
import org.example.repositary.UserRepositary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
}
