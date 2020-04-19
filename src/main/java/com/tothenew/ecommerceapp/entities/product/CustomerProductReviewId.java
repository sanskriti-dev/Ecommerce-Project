package com.tothenew.ecommerceapp.entities.product;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CustomerProductReviewId implements Serializable {

    @Column(name = "customer_user_id")
    private Long customerUserId;
    @Column(name = "product_id")
    private Long productId;

    public Long getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(Long customerUserId) {
        this.customerUserId = customerUserId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerProductReviewId that = (CustomerProductReviewId) o;
        return Objects.equals(customerUserId, that.customerUserId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerUserId, productId);
    }
}
