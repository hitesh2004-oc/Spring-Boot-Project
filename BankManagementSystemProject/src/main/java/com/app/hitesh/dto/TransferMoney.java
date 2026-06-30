package com.app.hitesh.dto;

import lombok.Data;

@Data
public class TransferMoney {

    private String fromAccount;

    private String toAccount;

    private Double amount;
}