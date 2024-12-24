package com.neilsagarsahu.accounts.entity;

import com.neilsagarsahu.accounts.audit.AuditAwareImpl;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Data @ToString
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;


    @Column(updatable = false)
    @CreatedBy
    private String createdBy;


    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(insertable = false)
    @LastModifiedBy
    private String updatedBy;

//    @PrePersist
//    public void onPrePersist() {
//        this.createdAt = LocalDateTime.now();
//        this.createdBy = "ACCOUNTS_MS";
//    }
//
//    @PreUpdate
//    public void onPreUpdate() {
//        this.updatedAt = LocalDateTime.now();
//        this.updatedBy = "ACCOUNTS_MS";  // Replace with dynamic value if needed
//    }

}
