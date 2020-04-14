package com.tothenew.ecommerceapp.entities.product;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

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
}
