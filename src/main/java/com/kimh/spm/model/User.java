package com.kimh.spm.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @Column(name = "US_ID", length = 50, nullable = false)
    private String id;
    
    @Column(name = "US_PW", length = 100, nullable = false)
    private String password;
    
    @Column(name = "US_NM", length = 100, nullable = false)
    private String name;
    
    @Column(name = "US_EMAIL", length = 100, nullable = false)
    private String email;
    
    @Column(name = "IN_DATE")
    private LocalDateTime createdDate;
    
    @Column(name = "IN_USER")
    private LocalDateTime createdBy;
    
    @Column(name = "UPD_DATE")
    private LocalDateTime updatedDate;
    
    @Column(name = "UPD_USER")
    private LocalDateTime updatedBy;
    
    @Column(name = "US_ROLE", length = 10, nullable = false)
    private String role = "USER";
}
