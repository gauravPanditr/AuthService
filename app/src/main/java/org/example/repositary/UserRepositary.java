package org.example.repositary;

import org.example.enities.UserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepositary extends CrudRepository<UserInfo,Long> {


    Optional<UserInfo> findById(Long aLong);

    public UserInfo findByUsername(String username);




}
