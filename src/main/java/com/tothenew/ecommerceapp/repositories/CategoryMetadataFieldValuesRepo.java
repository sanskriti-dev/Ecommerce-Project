package com.tothenew.ecommerceapp.repositories;

import com.tothenew.ecommerceapp.entities.category.CategoryMedataFieldCompositeId;
import com.tothenew.ecommerceapp.entities.category.CategoryMetadataFieldValues;
import org.springframework.data.repository.CrudRepository;

public interface CategoryMetadataFieldValuesRepo extends CrudRepository<CategoryMetadataFieldValues, CategoryMedataFieldCompositeId> {
}
