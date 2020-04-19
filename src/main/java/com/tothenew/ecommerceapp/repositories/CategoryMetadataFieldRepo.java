package com.tothenew.ecommerceapp.repositories;

import com.tothenew.ecommerceapp.entities.category.CategoryMetadataField;
import org.springframework.data.repository.CrudRepository;

public interface CategoryMetadataFieldRepo extends CrudRepository<CategoryMetadataField,Long> {
    CategoryMetadataField findByName(String name);
}
