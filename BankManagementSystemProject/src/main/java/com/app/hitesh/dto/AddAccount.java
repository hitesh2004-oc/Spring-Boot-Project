package com.app.hitesh.dto;

import lombok.Data;

@Data
public class AddAccount {

    private String name;

    private String accountNumber;

    private Double balance;

    private String branch;
}