package com.tothenew.ecommerceapp.repositories;

import com.tothenew.ecommerceapp.entities.users.ForgotPasswordToken;
import org.springframework.data.repository.CrudRepository;

public interface ForgotPasswordTokenRepo extends CrudRepository<ForgotPasswordToken,Long> {
    ForgotPasswordToken findByUserEmail(String email);
    void deleteByUserEmail(String email);
}
