package com.app.hitesh.service;

import com.app.hitesh.entity.Account;
import com.app.hitesh.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository ar;

    // Add Account
    public Account addAccount(Account acc) {

        return ar.save(acc);
    }

    // Show All Accounts
    public List<Account> showAccounts() {

        return ar.findAll();
    }

    // Get Account By Id
    public Optional<Account> getAccountById(Integer id) {

        return ar.findById(id);
    }

    // Delete Account
    public void deleteAccount(Integer id) {

        if(!ar.existsById(id)) {

            throw new RuntimeException("Account Not Found");
        }

        ar.deleteById(id);
    }

    // Update Account
    public Account updateAccount(
            Integer id,
            Account update) {

        Account acc = ar.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found"));

        acc.setName(update.getName());
        acc.setAccountNumber(update.getAccountNumber());
        acc.setBalance(update.getBalance());
        acc.setBranch(update.getBranch());

        return ar.save(acc);
    }

    // Search Account
    public List<Account> searchAccount(
            String keyword) {

        return ar.findByNameContainingIgnoreCase(
                keyword);
    }
}