package com.cybertek.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@MappedSuperclass //because I will inherit from this class

public class BaseEntity {
    @Id
    //I want postgres to create one for me:
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (nullable = false, updatable = false)
    private LocalDateTime insertDateTime;
    @Column (nullable = false, updatable = false)
    private Long insertUserId;
    @Column (updatable = false)
    private LocalDateTime lastUpdatedDateTime;
    @Column (updatable = false)
    private Long lastUpdateUserId;
    private Boolean isDeleted=false;

    @PrePersist
    private void onPrePersist(){
        this.insertDateTime=LocalDateTime.now();
        this.lastUpdatedDateTime=LocalDateTime.now();
        this.insertUserId=1L;
        this.lastUpdateUserId=1L;
    }

    private void onPreUpdate()
    {
        this.lastUpdatedDateTime=LocalDateTime.now();
        this.lastUpdateUserId=1L;
    }

}
