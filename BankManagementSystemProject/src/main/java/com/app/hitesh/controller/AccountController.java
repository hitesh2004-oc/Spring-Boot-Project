package com.app.hitesh.controller;

import com.app.hitesh.entity.Account;
import com.app.hitesh.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService account;

    // Add Account
    @PostMapping
    public ResponseEntity<Account> addAccount(
            @RequestBody Account acc) {

        return ResponseEntity.ok(
                account.addAccount(acc));
    }

    // Show Accounts
    @GetMapping("/show")
    public List<Account> showAccounts() {

        return account.showAccounts();
    }

    // Get By Id
    @GetMapping("/show/{id}")
    public ResponseEntity<Account> getAccount(
            @PathVariable Integer id) {

        Account a = account
                .getAccountById(id)
                .orElseThrow();

        return ResponseEntity.ok(a);
    }

    // Update
    @PutMapping("/update/{id}")
    public ResponseEntity<Account> updateAccount(
            @PathVariable Integer id,
            @RequestBody Account acc) {

        return ResponseEntity.ok(
                account.updateAccount(id, acc));
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(
            @PathVariable Integer id) {

        account.deleteAccount(id);

        return ResponseEntity.ok(
                "Account Deleted");
    }

    // Search
    @GetMapping("/search")
    public List<Account> searchAccount(
            @RequestParam String name) {

        return account.searchAccount(name);
    }
}