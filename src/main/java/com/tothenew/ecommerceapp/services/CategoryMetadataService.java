package com.tothenew.ecommerceapp.services;

import com.tothenew.ecommerceapp.dtos.CategoryMetadataDTO;
import com.tothenew.ecommerceapp.entities.category.Category;
import com.tothenew.ecommerceapp.entities.category.CategoryMetadataField;
import com.tothenew.ecommerceapp.entities.category.CategoryMetadataFieldValues;
import com.tothenew.ecommerceapp.exceptions.ResourceNotFoundException;
import com.tothenew.ecommerceapp.repositories.CategoryMetadataFieldRepo;
import com.tothenew.ecommerceapp.repositories.CategoryMetadataFieldValuesRepo;
import com.tothenew.ecommerceapp.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryMetadataService {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    CategoryMetadataFieldRepo metadataRepo;

    @Autowired
    CategoryMetadataFieldValuesRepo valuesRepo;

    public String addCategoryMetadata(CategoryMetadataDTO categoryMetadataDTO) {
        System.out.println(categoryMetadataDTO);
        Optional<Category> category = categoryRepo.findById(categoryMetadataDTO.getCategoryId());
        System.out.println(category.get()+"-----------");
        if (!category.isPresent()) {
            throw new ResourceNotFoundException(categoryMetadataDTO.getCategoryId() + " category does not exist");
        }
        HashMap<String, HashSet<String>> filedIdValues = categoryMetadataDTO.getFiledIdValues();
        Set<String> metadataIds = filedIdValues.keySet();
        metadataIds.forEach(id->{
            Optional<CategoryMetadataField> metadata = metadataRepo.findById(Long.parseLong(id));
            if (!metadata.isPresent()) {
                throw new ResourceNotFoundException(id + " metadata filed does not exist");
            }
        });
        metadataIds.forEach(id->{
            if (filedIdValues.get(id).isEmpty()) {
                throw new ResourceNotFoundException("any one filed does not have values");
            }
        });
        CategoryMetadataFieldValues fieldValues = new CategoryMetadataFieldValues();
        fieldValues.setCategory(category.get());
        System.out.println(metadataIds);
        metadataIds.forEach(id->{
            int count=0;
            Optional<CategoryMetadataField> metadata = metadataRepo.findById(Long.parseLong(id));
            fieldValues.setCategoryMetadataField(metadata.get());
            HashSet<String> values = filedIdValues.get(id);
            String value= String.join(",",values);
            fieldValues.setValue(value);
            metadata.get().getCategoryMetadataFieldValues().add(fieldValues);
            metadataRepo.save(metadata.get());
            count++;
            System.out.println(count+"__________________");
        });
        category.get().getCategoryMetadataFieldValues().add(fieldValues);

//        categoryRepo.save(category.get());
//        valuesRepo.save(fieldValues);
        System.out.println();
        return "Success";
    }
}
