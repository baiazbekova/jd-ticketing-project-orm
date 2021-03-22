package com.cybertek.entity;

import com.cybertek.enums.Gender;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean enabled;
    private String phone;

    @ManyToOne (fetch=FetchType.LAZY)
    //many roles can be assigned to one user
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

}
