package com.tothenew.ecommerceapp.repositories;

import com.tothenew.ecommerceapp.entities.category.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepo extends CrudRepository<Category,Long> {

    Category findByName(String name);
    @Query(value = "select * from category where parent_id=:id",nativeQuery = true)
    Optional<Category> findByParentId(@Param("id") Long id);
}
