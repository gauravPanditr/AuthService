package org.example.repositary;


import org.example.enities.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface RefreshTokenRepositary extends CrudRepository<RefreshToken, Integer> {


    Optional<RefreshToken> findByToken(String token);
}
