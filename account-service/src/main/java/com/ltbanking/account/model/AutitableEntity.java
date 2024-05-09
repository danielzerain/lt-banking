package com.ltbanking.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public class AutitableEntity {
    @CreatedDate
    @JsonIgnore
    private LocalDateTime createDate;
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime updateDate;
}
