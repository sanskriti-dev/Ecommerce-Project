package com.tothenew.ecommerceapp.repositories;

import com.tothenew.ecommerceapp.entities.users.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User,Long> {
    User findByEmail(String email);
    Optional<User> findById(Long id);
}
