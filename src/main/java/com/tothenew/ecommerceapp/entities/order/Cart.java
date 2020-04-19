package com.tothenew.ecommerceapp.entities.order;

import com.tothenew.ecommerceapp.entities.product.ProductVariation;
import com.tothenew.ecommerceapp.entities.users.Customer;

import javax.persistence.*;

@Entity
public class Cart {

    @EmbeddedId
    private CustomerProductVariationId customerProductVariationId;

    private Integer quantity;
    private Boolean isWishListItem;

    @OneToOne
    @JoinColumn(name = "customer_user_id",updatable = false,insertable = false)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "product_variation_id",updatable = false,insertable = false)
    private ProductVariation productVariation;

    public CustomerProductVariationId getCustomerProductVariationId() {
        return customerProductVariationId;
    }

    public void setCustomerProductVariationId(CustomerProductVariationId customerProductVariationId) {
        this.customerProductVariationId = customerProductVariationId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getWishListItem() {
        return isWishListItem;
    }

    public void setWishListItem(Boolean wishListItem) {
        isWishListItem = wishListItem;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ProductVariation getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariation productVariation) {
        this.productVariation = productVariation;
    }
}
