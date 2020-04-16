package com.tothenew.ecommerceapp.services;

import com.tothenew.ecommerceapp.entities.users.ForgotPasswordToken;
import com.tothenew.ecommerceapp.entities.users.User;
import com.tothenew.ecommerceapp.repositories.ForgotPasswordRepo;
import com.tothenew.ecommerceapp.repositories.UserRepo;
import com.tothenew.ecommerceapp.utils.SendEmail;
import com.tothenew.ecommerceapp.utils.ValidEmail;
import com.tothenew.ecommerceapp.utils.ValidPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
public class ForgotPasswordService {

    @Autowired
    ValidEmail validEmail;
    @Autowired
    UserRepo userRepo;
    @Autowired
    SendEmail sendEmail;
    @Autowired
    ForgotPasswordRepo forgotPasswordRepo;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String sendToken(String email) {

        if (validEmail.checkEmailValid(email)) {
            String token = UUID.randomUUID().toString();
            ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken();
            User user = userRepo.findByEmail(email);
            try {
                if (user.getEmail().equals(null)) {
                }
            } catch (NullPointerException ex) {
                return "Email should exist in the database";
            }
            forgotPasswordToken.setToken(token);
            forgotPasswordToken.setId(user.getId());
            forgotPasswordToken.setUserEmail(email);
            forgotPasswordToken.setExpiryDate(new Date());
            return "Success";
        } else
            return "email not valid";

    }

    @Transactional
    public String resetPassword(String email, String token, String pass, String cpass) {
        if (!pass.equals(cpass)) {
            return "password and confirm password not match";
        }
        if (!ValidPassword.isValidPassword(pass)) {
            return "in valid password syntax";
        }
        ForgotPasswordToken forgotPasswordToken = forgotPasswordRepo.findUserByEmail(email);
        try {
            if (forgotPasswordToken.getUserEmail().equals(null)) {
            }
        } catch (NullPointerException ex) {
            return "no email found";
        }
        Date date = new Date();
        long diff = date.getTime() - forgotPasswordToken.getExpiryDate().getTime();
        long diffHours = diff / (60 * 60 * 1000);
        if (diffHours > 24) {
            forgotPasswordRepo.deleteByUserEmail(email);
            return "Token has expired";
        }
        if (!forgotPasswordToken.getToken().equals(token)) {
            return "invalid token";
        }
        if (forgotPasswordToken.getToken().equals(token)) {
            User user = userRepo.findByEmail(email);
            user.setPassword(passwordEncoder.encode(pass));
            userRepo.save(user);
            forgotPasswordRepo.deleteByUserEmail(email);
            sendEmail.sendEmail("PASSWORD CHANGED", "YOUR PASSWORD HAS BEEN CHANGED", email);
            return "Success";
        }
        return "Success";
    }
}

