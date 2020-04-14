package com.tothenew.ecommerceapp.entities.utils;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class AuditingInformation {

    private Date dateCreated;
    private Date lastUpdated;
    private String createdBy;
    private String updatedBy;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "AuditingInformation{" +
                "dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
