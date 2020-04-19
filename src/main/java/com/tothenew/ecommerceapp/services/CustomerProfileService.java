package com.tothenew.ecommerceapp.services;

import com.tothenew.ecommerceapp.dtos.AddressDTO;
import com.tothenew.ecommerceapp.dtos.CustomerProfileDTO;
import com.tothenew.ecommerceapp.entities.users.Address;
import com.tothenew.ecommerceapp.entities.users.Customer;
import com.tothenew.ecommerceapp.exceptions.ContactInvalidException;
import com.tothenew.ecommerceapp.exceptions.InvalidPasswordException;
import com.tothenew.ecommerceapp.exceptions.ResourceNotFoundException;
import com.tothenew.ecommerceapp.repositories.AddressRepo;
import com.tothenew.ecommerceapp.repositories.CustomerRepo;
import com.tothenew.ecommerceapp.utils.SendEmail;
import com.tothenew.ecommerceapp.utils.UserEmailFromToken;
import com.tothenew.ecommerceapp.utils.ValidPassword;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerProfileService {

    @Autowired
    UserEmailFromToken userEmailFromToken;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SendEmail sendEmail;

    @Autowired
    AddressRepo addressRepo;

    public Customer viewProfile(HttpServletRequest request) {
        String customerEmail = userEmailFromToken.getUserEmail(request);
        Customer customer = customerRepo.findByEmail(customerEmail);
        return customer;
    }

    public String updateCustomer(CustomerProfileDTO customerProfileDTO,HttpServletRequest request) {
        if (!(customerProfileDTO.getContact() == null) && (customerProfileDTO.getContact().length()!=10)) {
            throw new ContactInvalidException("invalid contact");
        }
        Customer customer = customerRepo.findByEmail(userEmailFromToken.getUserEmail(request));
        try {
            if (!(customerProfileDTO.getFirstName()  == null)){
                customer.setFirstName(customerProfileDTO.getFirstName());
            }
            if (!(customerProfileDTO.getLastName() == null)){
                customer.setLastName(customerProfileDTO.getLastName());
            }
            if (!(customerProfileDTO.getContact() == null)) {
                customer.setContact(customerProfileDTO.getContact());
            }
            if (!(customerProfileDTO.getImage() == null)) {
                //to do -> check image format then update image
            }
        } catch (NullPointerException ex) {
        }

        customerRepo.save(customer);
        return "Success";
    }

    public String updatePassword(String pass,String cPass,HttpServletRequest request) {
        if (!pass.contentEquals(cPass)) {
            return "Password and confirm password does not match";
        }
        if (!ValidPassword.isValidPassword(pass)) {
            throw new InvalidPasswordException("password format invalid");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Customer customer = customerRepo.findByEmail(userEmailFromToken.getUserEmail(request));
        customer.setPassword(passwordEncoder.encode(pass));

        customerRepo.save(customer);

        sendEmail.sendEmail("PASSWORD CHANGED","Your password changed",customer.getEmail());

        return "Success";
    }

    public String newAddress(AddressDTO addressDTO,HttpServletRequest request) {
        Customer customer = customerRepo.findByEmail(userEmailFromToken.getUserEmail(request));
        Address address = modelMapper.map(addressDTO,Address.class);
        Set<Address> addresses = customer.getAddresses();
        addresses.add(address);
        customer.setAddresses(addresses);
        addresses.forEach(a -> {
            Address addressSave = a;
            addressSave.setUser(customer);
        });
        customerRepo.save(customer);
        return "Success";
    }

    public Set<AddressDTO> viewAddress(HttpServletRequest request) {
        Customer customer = customerRepo.findByEmail(userEmailFromToken.getUserEmail(request));
        Set<Address> addresses = customer.getAddresses();
        Set<AddressDTO> addressDTOs = new HashSet<>();
        addresses.forEach(a -> {
            AddressDTO addressDTO = modelMapper.map(a,AddressDTO.class);
            addressDTOs.add(addressDTO);
        });
        return addressDTOs;
    }

    @Transactional
    public String deleteAddress(Long id,HttpServletRequest request) {
        Optional<Address> address = addressRepo.findById(id);
        if (!address.isPresent()) {
            throw  new ResourceNotFoundException("no address fount with id " + id);
        }
        addressRepo.deleteById(id);
        return "Success";
    }

    public String updateAddress(Long id,AddressDTO addressDTO,HttpServletRequest request) {
        Optional<Address> address = addressRepo.findById(id);
        if (!address.isPresent()) {
            throw  new ResourceNotFoundException("no address fount with id " + id);
        }
        Customer customer = customerRepo.findByEmail(userEmailFromToken.getUserEmail(request));
        Set<Address> addresses = customer.getAddresses();
        addresses.forEach(a->{
            if (a.getId() == address.get().getId()) {
                a.setAddress(addressDTO.getAddress());
                a.setCity(addressDTO.getCity());
                a.setCountry(addressDTO.getCountry());
                a.setLabel(addressDTO.getLabel());
                a.setState(addressDTO.getState());
                a.setZipCode(addressDTO.getZipCode());
                a.setAddress(addressDTO.getAddress());
            }
        });
        customerRepo.save(customer);
        return "Success";
    }
}
