package com.app.hitesh.controller;

import com.app.hitesh.entity.Account;
import com.app.hitesh.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService ts;

    // Deposit
    @PostMapping("/deposit")
    public ResponseEntity<Account> deposit(
            @RequestParam String accountNumber,
            @RequestParam Double amount) {

        return ResponseEntity.ok(
                ts.deposit(accountNumber, amount));
    }

    // Withdraw
    @PostMapping("/withdraw")
    public ResponseEntity<Account> withdraw(
            @RequestParam String accountNumber,
            @RequestParam Double amount) {

        return ResponseEntity.ok(
                ts.withdraw(accountNumber, amount));
    }
}