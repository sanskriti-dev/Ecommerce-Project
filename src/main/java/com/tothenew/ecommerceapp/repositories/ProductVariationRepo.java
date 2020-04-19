package com.tothenew.ecommerceapp.repositories;

import com.tothenew.ecommerceapp.entities.product.ProductVariation;
import org.springframework.data.repository.CrudRepository;

public interface ProductVariationRepo extends CrudRepository<ProductVariation,Long> {
}
