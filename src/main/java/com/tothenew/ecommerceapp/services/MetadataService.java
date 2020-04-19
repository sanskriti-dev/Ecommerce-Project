package com.tothenew.ecommerceapp.services;

import com.tothenew.ecommerceapp.entities.category.CategoryMetadataField;
import com.tothenew.ecommerceapp.exceptions.FieldAlreadyExistException;
import com.tothenew.ecommerceapp.repositories.CategoryMetadataFieldRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetadataService {

    @Autowired
    CategoryMetadataFieldRepo metadataRepo;

    public String addMetadata(String fieldName) {
        if (metadataRepo.findByName(fieldName) != null) {
            throw new FieldAlreadyExistException(fieldName + " already exist");
        }
        CategoryMetadataField metadata = new CategoryMetadataField();
        metadata.setName(fieldName);
        metadataRepo.save(metadata);
        return "Success "+metadataRepo.findByName(fieldName).getId();
    }
}
