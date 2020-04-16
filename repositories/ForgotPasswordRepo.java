package com.tothenew.ecommerceapp.repositories;

import com.tothenew.ecommerceapp.entities.users.ForgotPasswordToken;
import com.tothenew.ecommerceapp.entities.users.User;
import org.springframework.data.repository.CrudRepository;

public interface ForgotPasswordRepo extends CrudRepository<ForgotPasswordToken,Long> {
    ForgotPasswordToken findUserByEmail(String email);
    void deleteByUserEmail(String email);
}
