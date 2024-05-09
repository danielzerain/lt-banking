package com.ltbanking.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public class AutitableEntity {
    @CreatedDate
    @JsonIgnore
    private LocalDateTime createDate;
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime updateDate;
}
