package com.app.hitesh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddEmployee {

    private String name;

    private String department;

    private Double salary;

    private String city;
}