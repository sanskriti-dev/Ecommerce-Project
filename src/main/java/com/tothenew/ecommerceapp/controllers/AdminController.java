package com.tothenew.ecommerceapp.controllers;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.tothenew.ecommerceapp.entities.users.Customer;
import com.tothenew.ecommerceapp.entities.users.Seller;
import com.tothenew.ecommerceapp.entities.users.User;
import com.tothenew.ecommerceapp.repositories.CustomerRepo;
import com.tothenew.ecommerceapp.repositories.SellerRepo;
import com.tothenew.ecommerceapp.repositories.UserRepo;
import com.tothenew.ecommerceapp.utils.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    SellerRepo sellerRepo;

    @Autowired
    SendEmail sendEmail;

    @PatchMapping("admin/activate/customer/{id}")
    public String activateCustomer(@PathVariable Long id, HttpServletResponse httpServletResponse) {
        Optional<User> user = userRepo.findById(id);
        if (!user.isPresent()) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "no user found with given id";
        }
        if (!user.get().isActive()) {
            user.get().setActive(true);
            userRepo.save(user.get());
            // trigger mail
            sendEmail.sendEmail("ACTIVATED", "HEY CUSTOMER YOUR ACCOUNT HAS BEEN ACTIVATED", user.get().getEmail());
            return "Success";
        }
        userRepo.save(user.get());
        System.out.println("already activated");
        return "Success";
    }

    @PatchMapping("admin/deactivate/customer/{id}")
    public String deactivateCustomer(@PathVariable Long id, HttpServletResponse httpServletResponse) {
        Optional<User> user = userRepo.findById(id);
        if (!user.isPresent()) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "no user found with given id";
        }
        if (user.get().isActive()) {
            user.get().setActive(false);
            userRepo.save(user.get());
            // trigger mail
            sendEmail.sendEmail("DEACTIVATED", "HEY CUSTOMER YOUR ACCOUNT HAS BEEN DEACTIVATED", user.get().getEmail());
            return "Success";
        }
        userRepo.save(user.get());
        System.out.println("already deactivated");
        return "Success";
    }

    @PatchMapping("admin/activate/seller/{id}")
    public String activateSeller(@PathVariable Long id, HttpServletResponse httpServletResponse) {
        Optional<User> user = userRepo.findById(id);
        if (!user.isPresent()) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "no user found with given id";
        }
        if (!user.get().isActive()) {
            user.get().setActive(true);
            userRepo.save(user.get());
            // trigger mail
            sendEmail.sendEmail("ACTIVATED", "HEY SELLER YOUR ACCOUNT HAS BEEN ACTIVATED", user.get().getEmail());
            return "Success";
        }
        userRepo.save(user.get());
        System.out.println("already activated");
        return "Success";
    }

    @PatchMapping("admin/deactivate/seller/{id}")
    public String deactivateSeller(@PathVariable Long id, HttpServletResponse httpServletResponse) {
        Optional<User> user = userRepo.findById(id);
        if (!user.isPresent()) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "no user found with given id";
        }
        if (user.get().isActive()) {
            user.get().setActive(false);
            userRepo.save(user.get());
            // trigger mail
            sendEmail.sendEmail("DEACTIVATED", "HEY SELLER YOUR ACCOUNT HAS BEEN DEACTIVATED", user.get().getEmail());
            return "Success";
        }
        userRepo.save(user.get());
        System.out.println("already deactivated");
        return "Success";
    }


    @GetMapping("/admin/customers")
    public MappingJacksonValue getCustomers(@RequestParam(defaultValue = "0") String page, @RequestParam(defaultValue = "10") String size, @RequestParam(defaultValue = "id") String SortBy) {
        List<Customer> customerList = customerRepo.findAll(PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), Sort.by(SortBy)));
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "firstName", "middleName", "lastName", "email", "active");
        FilterProvider filters = new SimpleFilterProvider().addFilter("ignoreAddressInCustomer", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(customerList);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/admin/sellers")
    public MappingJacksonValue getSellers(@RequestParam(defaultValue = "0") String page, @RequestParam(defaultValue = "10") String size, @RequestParam(defaultValue = "id") String SortBy) {
        List<Seller> sellerList = sellerRepo.findAll(PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), Sort.by(SortBy)));
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "firstName", "middleName", "lastName", "email", "active", "companyName", "addresses", "companyContact");
        FilterProvider filters = new SimpleFilterProvider().addFilter("ignoreAddressInCustomer", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(sellerList);
        mapping.setFilters(filters);

        return mapping;
    }
}
