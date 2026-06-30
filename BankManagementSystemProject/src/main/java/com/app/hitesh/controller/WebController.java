package com.app.hitesh.controller;

import com.app.hitesh.entity.Account;
import com.app.hitesh.service.AccountService;
import com.app.hitesh.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @Autowired
    private AccountService account;

    @Autowired
    private TransactionService transaction;

    // Home Page
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute(
                "accounts",
                account.showAccounts());

        return "index";
    }

    // Add Account Page
    @GetMapping("/add")
    public String addPage(Model model) {

        model.addAttribute(
                "account",
                new Account());

        return "add-account";
    }

    // Save Account
    @PostMapping("/save")
    public String saveAccount(
            @ModelAttribute Account acc) {

        account.addAccount(acc);

        return "redirect:/";
    }

    // Edit Page
    @GetMapping("/edit/{id}")
    public String editPage(
            @PathVariable Integer id,
            Model model) {

        Account a = account
                .getAccountById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account Not Found"));

        model.addAttribute(
                "account",
                a);

        return "update-account";
    }

    // Update Account
    @PostMapping("/update/{id}")
    public String updateAccount(
            @PathVariable Integer id,
            @ModelAttribute Account acc) {

        account.updateAccount(id, acc);

        return "redirect:/";
    }

    // Delete Account
    @GetMapping("/delete/{id}")
    public String deleteAccount(
            @PathVariable Integer id) {

        account.deleteAccount(id);

        return "redirect:/";
    }

    // Search Account
    @GetMapping("/search")
    public String searchAccount(
            @RequestParam String keyword,
            Model model) {

        model.addAttribute(
                "accounts",
                account.searchAccount(keyword));

        return "index";
    }

    // Deposit Page
    @GetMapping("/deposit")
    public String depositPage() {

        return "deposit";
    }

    // Deposit Money
    @PostMapping("/web/deposit")
    public String depositMoney(

            @RequestParam String accountNumber,

            @RequestParam Double amount) {

        transaction.deposit(
                accountNumber,
                amount);

        return "redirect:/";
    }

    // Withdraw Page
    @GetMapping("/withdraw")
    public String withdrawPage() {

        return "withdraw";
    }

    // Withdraw Money
    @PostMapping("/web/withdraw")
    public String withdrawMoney(

            @RequestParam String accountNumber,

            @RequestParam Double amount) {

        transaction.withdraw(
                accountNumber,
                amount);

        return "redirect:/";
    }

    // Transfer Page
    @GetMapping("/transfer")
    public String transferPage() {

        return "transfer";
    }

    // Transfer Money
    @PostMapping("/web/transfer")
    public String transferMoney(

            @RequestParam String fromAccount,

            @RequestParam String toAccount,

            @RequestParam Double amount) {

        transaction.transfer(
                fromAccount,
                toAccount,
                amount);

        return "redirect:/";
    }

    // Transaction History
    @GetMapping("/transactions")
    public String transactionHistory(
            Model model) {

        model.addAttribute(
                "transactions",
                transaction.showTransactions());

        return "transactions";
    }
}