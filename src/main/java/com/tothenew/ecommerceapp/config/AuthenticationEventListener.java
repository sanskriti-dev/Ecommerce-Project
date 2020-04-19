package com.tothenew.ecommerceapp.config;

import com.tothenew.ecommerceapp.entities.users.User;
import com.tothenew.ecommerceapp.entities.users.UserLoginFailCounter;
import com.tothenew.ecommerceapp.repositories.UserLoginFailCounterRepo;
import com.tothenew.ecommerceapp.repositories.UserRepo;
import com.tothenew.ecommerceapp.utils.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Optional;


@Component
public class AuthenticationEventListener {

    @Autowired
    UserLoginFailCounterRepo userLoginFailCounterRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    SendEmail sendEmail;

    @EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {
        int counter;
        String userEmail = (String) event.getAuthentication().getPrincipal();
        if ("access-token".contentEquals(userEmail)) {
            System.out.println("invalid access token");
            return;
        }
        System.out.println(userEmail+"----");
        Optional<UserLoginFailCounter> userLoginFailCounter = userLoginFailCounterRepo.findByEmail(userEmail);

        if (!userLoginFailCounter.isPresent()) {
            UserLoginFailCounter userLoginFailCounter1 = new UserLoginFailCounter();
            userLoginFailCounter1.setAttempts(1);
            userLoginFailCounter1.setEmail(userEmail);
            userLoginFailCounterRepo.save(userLoginFailCounter1);
        }
        if (userLoginFailCounter.isPresent()) {
            counter = userLoginFailCounter.get().getAttempts();
            System.out.println(counter);
            if (counter>=2) {
                User user = userRepo.findByEmail(userEmail);
                user.setLocked(true);
                sendEmail.sendEmail("ACCOUNT LOCKED","YOUR ACCOUNT HAS BEEN LOCKED",userEmail);
                userRepo.save(user);
            }
            UserLoginFailCounter userLoginFailCounter1 = userLoginFailCounter.get();
            userLoginFailCounter1.setAttempts(++counter);
            userLoginFailCounter1.setEmail(userEmail);
            System.out.println(userLoginFailCounter1+"-----------------");
            userLoginFailCounterRepo.save(userLoginFailCounter1);
        }

    }

    @EventListener
    public void authenticationPass(AuthenticationSuccessEvent event) {
        LinkedHashMap<String,String> userMap = new LinkedHashMap<>();
        try {
           userMap = (LinkedHashMap<String, String>) event.getAuthentication().getDetails();
        } catch (ClassCastException ex) {

        }
        String userEmail = new String();
        try {
            userEmail = userMap.get("username");
        } catch (NullPointerException e) {
        }
        Optional<UserLoginFailCounter> userLoginFailCounter = userLoginFailCounterRepo.findByEmail(userEmail);
        if (userLoginFailCounter.isPresent()){
            userLoginFailCounterRepo.deleteById(userLoginFailCounter.get().getId());
        }
    }

}

