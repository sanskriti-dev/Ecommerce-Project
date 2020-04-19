package com.tothenew.ecommerceapp.entities.product;

import com.tothenew.ecommerceapp.entities.users.Customer;
import com.tothenew.ecommerceapp.entities.utils.AuditingInformation;

import javax.persistence.*;

@Entity
public class ProductReview {

    @EmbeddedId
    private CustomerProductReviewId customerProductReviewId;

    private String review;
    private Integer rating;

    @Embedded
    private AuditingInformation auditingInformation;

    @ManyToOne
    @JoinColumn(name = "customer_user_id",insertable = false,updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id",insertable = false,updatable = false)
    private Product product;

    public CustomerProductReviewId getCustomerProductReviewId() {
        return customerProductReviewId;
    }

    public void setCustomerProductReviewId(CustomerProductReviewId customerProductReviewId) {
        this.customerProductReviewId = customerProductReviewId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public AuditingInformation getAuditingInformation() {
        return auditingInformation;
    }

    public void setAuditingInformation(AuditingInformation auditingInformation) {
        this.auditingInformation = auditingInformation;
    }
}
