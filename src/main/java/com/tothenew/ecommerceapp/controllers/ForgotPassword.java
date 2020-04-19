package com.tothenew.ecommerceapp.controllers;

import com.tothenew.ecommerceapp.services.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class ForgotPassword {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    ForgotPasswordService forgotPasswordService;

    @PostMapping("/token/{email}")
    public String getToken(@PathVariable String email, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String getMessage = forgotPasswordService.sendToken(email);
        System.out.println(getMessage);
        if (getMessage.equals("Success")) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null) {
                String tokenValue = authHeader.replace("Bearer", "").trim();
                OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
                tokenStore.removeAccessToken(accessToken);
            }
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getMessage;
    }

    @PatchMapping("/resetPassword")
    public String resetPassword(@RequestParam String email, @RequestParam String token, @RequestParam String pass, @RequestParam String cpass, HttpServletResponse httpServletResponse) {
        String getMessage = forgotPasswordService.resetPassword(email, token, pass, cpass);
        if (getMessage.equals("Success")) {
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return getMessage;
    }
}
