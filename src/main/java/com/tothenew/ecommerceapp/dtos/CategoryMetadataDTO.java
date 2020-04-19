package com.tothenew.ecommerceapp.dtos;

import java.util.HashMap;
import java.util.HashSet;

public class CategoryMetadataDTO {

    private Long categoryId;
    private HashMap<String, HashSet<String>> filedIdValues;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public HashMap<String, HashSet<String>> getFiledIdValues() {
        return filedIdValues;
    }

    public void setFiledIdValues(HashMap<String, HashSet<String>> filedIdValues) {
        this.filedIdValues = filedIdValues;
    }

    @Override
    public String toString() {
        return "CategoryMetadataDTO{" +
                "categoryId=" + categoryId +
                ", filedIdValues=" + filedIdValues +
                '}';
    }
}
