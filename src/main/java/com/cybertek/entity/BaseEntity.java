package com.cybertek.entity;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
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

    private LocalDateTime insertDateTime;
    private Long insertUserId;
    private LocalDateTime lastUpdatedDateTime;
    private long lastUpdateUserId;

    private Boolean isDeleted=false;
}
