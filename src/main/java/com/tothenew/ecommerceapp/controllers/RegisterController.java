package com.tothenew.ecommerceapp.controllers;

import com.tothenew.ecommerceapp.entities.users.Customer;
import com.tothenew.ecommerceapp.entities.users.Seller;
import com.tothenew.ecommerceapp.services.UserRegisterService;
import com.tothenew.ecommerceapp.utils.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class RegisterController {

    @Autowired
    UserRegisterService userRegisterService;

    @Autowired
    SendEmail sendEmail;

    @PostMapping("register/customer")
    String registerCustomer(@Valid @RequestBody Customer customer, HttpServletResponse httpServletResponse) {

        String getMessage = userRegisterService.registerCustomer(customer);
        // use logger
        if ("Success".contentEquals(getMessage)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getMessage;
    }

    @PostMapping("register/seller")
    String registerSeller(@Valid @RequestBody Seller seller, HttpServletResponse httpServletResponse) {
        String getMessage = userRegisterService.registerSeller(seller);
        if (getMessage.contentEquals("Success")) {
            sendEmail.sendEmail("ACCOUNT CREATED", "Your account has been created waiting for approval", seller.getEmail());
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getMessage;
    }


}
