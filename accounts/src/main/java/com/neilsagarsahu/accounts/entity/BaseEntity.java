package com.neilsagarsahu.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
@ToString
public class BaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdAt;


    @Column(updatable = false)
    private String createdBy;


    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @Column(insertable = false)
    private String updatedBy;

    @PrePersist
    public void onPrePersist() {
        this.createdAt = LocalDateTime.now();
        this.createdBy = "ACCOUNTS_MS";
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "ACCOUNTS_MS";  // Replace with dynamic value if needed
    }

}
