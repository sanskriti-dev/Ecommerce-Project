package com.tothenew.ecommerceapp.controllers;

import com.tothenew.ecommerceapp.services.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/metadata")
public class MetadataController {

    @Autowired
    MetadataService metadataService;

    @PostMapping("/add")
    public String addMetadata(@RequestParam String fieldName, HttpServletResponse response) {
        String getMessage = metadataService.addMetadata(fieldName);
        if (getMessage.contains("Success")) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getMessage;
    }
}
