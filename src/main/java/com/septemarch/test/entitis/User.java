package com.septemarch.test.entitis;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private Long id;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name="user_name")
    private String user_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "birth_date")
    private Date birth_date;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    @Pattern(regexp = "^\\+\\d{12}$")
    private String phone_number;
}
