package com.tothenew.ecommerceapp.repositories;

import com.tothenew.ecommerceapp.entities.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepo extends CrudRepository<Product,Long> {

    @Query(value = "select * from product where category_id=:id",nativeQuery = true)
    Product findByCategoryId(@Param("id") Long id);
}
