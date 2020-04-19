package com.tothenew.ecommerceapp.services;

import com.tothenew.ecommerceapp.entities.category.Category;
import com.tothenew.ecommerceapp.exceptions.FieldAlreadyExistException;
import com.tothenew.ecommerceapp.exceptions.ResourceNotFoundException;
import com.tothenew.ecommerceapp.repositories.CategoryRepo;
import com.tothenew.ecommerceapp.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ProductRepo productRepo;

    public String addCategory(String name, Optional<Long> parentId) {
        if (categoryRepo.findByName(name) != null) {
            throw new FieldAlreadyExistException(name + " category already exist");
        }
        Category category = new Category();
        if (parentId.isPresent()) {
            if (productRepo.findByCategoryId(parentId.get()) != null) {
                return "parent id is already associated with some product";
            }
            else {
                category.setName(name);
                category.setParentId(categoryRepo.findById(parentId.get()).get());
                categoryRepo.save(category);
                return "Success " + categoryRepo.findByName(name).getId();
            }
        }
        if (!parentId.isPresent()) {
            category.setName(name);
            categoryRepo.save(category);
            return "Success " + categoryRepo.findByName(name).getId();
        }
        return "Success " + categoryRepo.findByName(name).getId();
    }

    @Transactional
    public String deleteCategory(Long id) {
        if (!categoryRepo.findById(id).isPresent()) {
            throw new ResourceNotFoundException(id + " category does not exist");
        }
        if (productRepo.findByCategoryId(id) != null) {
            return "id is associated with some product, cannot delete";
        }
        if (categoryRepo.findByParentId(id).isPresent()) {
            return "id is a parent category, cannot delete";
        }
        categoryRepo.deleteById(id);
        return "Success";
    }

    public String updateCategory(String name,Long id) {
        if (!categoryRepo.findById(id).isPresent()) {
            throw new ResourceNotFoundException(id + " category does not exist");
        }
        if (categoryRepo.findByName(name) != null) {
            throw new FieldAlreadyExistException(name + " category already exist");
        }
        Optional<Category> category = categoryRepo.findById(id);
        Category updateCategory = category.get();
        updateCategory.setName(name);
        categoryRepo.save(updateCategory);
        return "Success";
    }
}
