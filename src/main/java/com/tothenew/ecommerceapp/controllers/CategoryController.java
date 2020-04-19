package com.tothenew.ecommerceapp.controllers;

import com.tothenew.ecommerceapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public String addCategory(@RequestParam String name, @RequestParam(required = false) Optional<Long> parentId, HttpServletResponse response) {
        String getMessage = categoryService.addCategory(name,parentId);
        if (getMessage.contains("Success")) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getMessage;
    }

    @DeleteMapping("/delete")
    public String deleteCategory(@RequestParam Long id,HttpServletResponse response) {
        String getMessage = categoryService.deleteCategory(id);
        if ("Success".contentEquals(getMessage)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getMessage;
    }

    @PutMapping("/update")
    public String updateCategory(@RequestParam Long id,@RequestParam String name,HttpServletResponse response) {
        String getMessage = categoryService.updateCategory(name,id);
        if ("Success".contentEquals(getMessage)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return getMessage;
    }
}
