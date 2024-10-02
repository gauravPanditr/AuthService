package org.example.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.enities.UserInfo;
import org.example.model.UserInfoDto;
import org.example.repositary.UserRepositary;
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
