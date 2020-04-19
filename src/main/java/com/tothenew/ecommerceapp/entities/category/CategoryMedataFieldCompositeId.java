package com.tothenew.ecommerceapp.entities.category;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CategoryMedataFieldCompositeId implements Serializable {

    @Column(name = "category_metadata_field_id")
    private Long categoryMetadataFieldId;
    @Column(name = "category_id")
    private Long categoryId;

    public Long getCategoryMetadataFieldId() {
        return categoryMetadataFieldId;
    }

    public void setCategoryMetadataFieldId(Long categoryMetadataFieldId) {
        this.categoryMetadataFieldId = categoryMetadataFieldId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryMedataFieldCompositeId that = (CategoryMedataFieldCompositeId) o;
        return Objects.equals(categoryMetadataFieldId, that.categoryMetadataFieldId) &&
                Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryMetadataFieldId, categoryId);
    }
}
