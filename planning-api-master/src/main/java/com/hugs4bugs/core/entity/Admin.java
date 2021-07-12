package com.hugs4bugs.core.entity;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@DiscriminatorValue("ADMIN")
@Table(name = "User")
@Entity
@NoArgsConstructor
public class Admin extends User {

    public Admin(@NonNull String firstName,
                 @NonNull String lastName,
                 @NonNull String code,
                 @NonNull String role,
                 @NonNull String password) {
        super(firstName, lastName, code, role, password);
    }

}
