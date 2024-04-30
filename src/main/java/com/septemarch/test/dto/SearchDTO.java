package com.septemarch.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class SearchDTO {
    private LocalDate from;
    private LocalDate to;
}
