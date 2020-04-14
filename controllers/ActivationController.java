package com.tothenew.ecommerceapp.controllers;

import com.tothenew.ecommerceapp.services.CustomerActivateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ActivationController {

    @Autowired
    CustomerActivateService customerActivateService;

    @PutMapping("customer/activate/{token}")
    public String activateCustomer(@PathVariable String token, HttpServletResponse httpServletResponse) {
        String getMessage = customerActivateService.activateCustomer(token);
        if (getMessage.equals("Success")) {
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getMessage;
    }

    @PostMapping("/customer/re-sendActivation")
    public String resendLink(@RequestParam String email, HttpServletResponse httpServletResponse) {
        String getMessage = customerActivateService.resendLink(email);
        if (getMessage.equals("Success")) {
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getMessage;
    }
}
