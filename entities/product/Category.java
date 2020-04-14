package com.tothenew.ecommerceapp.entities.product;

import com.tothenew.ecommerceapp.entities.utils.AuditingInformation;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true,nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id",referencedColumnName = "id",nullable = true)
    private Category parentId;

    @Embedded
    private AuditingInformation auditingInformation;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentId() {
        return parentId;
    }

    public void setParentId(Category parentId) {
        this.parentId = parentId;
    }

    public AuditingInformation getAuditingInformation() {
        return auditingInformation;
    }

    public void setAuditingInformation(AuditingInformation auditingInformation) {
        this.auditingInformation = auditingInformation;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
