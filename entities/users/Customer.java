package com.tothenew.ecommerceapp.entities.users;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tothenew.ecommerceapp.entities.product.ProductReview;

import javax.persistence.*;
import java.util.Set;


@Entity
@PrimaryKeyJoinColumn(name="user_id")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFilter("ignoreAddressInCustomer")
public class Customer extends User {

    private String contact;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<ProductReview> productReviews;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Set<ProductReview> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(Set<ProductReview> productReviews) {
        this.productReviews = productReviews;
    }
}
