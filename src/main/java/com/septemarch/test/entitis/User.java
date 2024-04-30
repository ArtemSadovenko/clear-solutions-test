package com.septemarch.test.entitis;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email")
    @Email(message = "Wrong Email format")
    private String email;

    @Column(name="user_name")
    private String user_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "birth_date")
    private LocalDate birth_date;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    @Pattern(regexp = "^\\+\\d{12}$", message = "Wrong phone number")
    private String phone_number;
}
