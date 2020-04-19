package com.tothenew.ecommerceapp.repositories;

import com.tothenew.ecommerceapp.entities.users.UserLoginFailCounter;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserLoginFailCounterRepo extends CrudRepository<UserLoginFailCounter,Long> {
    Optional<UserLoginFailCounter> findByEmail(String email);
}
