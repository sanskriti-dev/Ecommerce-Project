package com.tothenew.ecommerceapp.repositories;

import com.tothenew.ecommerceapp.entities.users.Seller;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SellerRepo extends CrudRepository<Seller,Long> {
    List<Seller> findByGst(String gst);
    Seller findByCompanyName(String companyName);
    List<Seller> findAll(Pageable pageable);
}
