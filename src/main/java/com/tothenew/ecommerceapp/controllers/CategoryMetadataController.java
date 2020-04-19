package com.tothenew.ecommerceapp.controllers;

import com.tothenew.ecommerceapp.dtos.CategoryMetadataDTO;
import com.tothenew.ecommerceapp.services.CategoryMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;

@RestController
@RequestMapping("/categoryMetadata")
public class CategoryMetadataController {

    @Autowired
    private CategoryMetadataService categoryMetadataService;

    @PostMapping("/add")
    public String addCategoryMetadata(@RequestBody CategoryMetadataDTO categoryMetadataDTO, HttpServletResponse response) {
        String getMessage = categoryMetadataService.addCategoryMetadata(categoryMetadataDTO);
        if ("Success".contentEquals(getMessage)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getMessage;
    }

}
