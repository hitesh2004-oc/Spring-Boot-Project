package com.app.hitesh.service;

import com.app.hitesh.entity.Account;
import com.app.hitesh.entity.Transaction;
import com.app.hitesh.repository.AccountRepository;
import com.app.hitesh.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository ar;

    @Autowired
    private TransactionRepository tr;

    // Deposit
    public Account deposit(
            String accountNumber,
            Double amount) {

        Account acc = ar.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account Not Found"));

        acc.setBalance(
                acc.getBalance() + amount);

        ar.save(acc);

        Transaction t = new Transaction();

        t.setType("DEPOSIT");
        t.setAmount(amount);
        t.setDate(LocalDateTime.now());
        t.setAccount(acc);

        tr.save(t);

        return acc;
    }

    // Withdraw
    public Account withdraw(
            String accountNumber,
            Double amount) {

        Account acc = ar.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account Not Found"));

        if(acc.getBalance() < amount) {

            throw new RuntimeException(
                    "Insufficient Balance");
        }

        acc.setBalance(
                acc.getBalance() - amount);

        ar.save(acc);

        Transaction t = new Transaction();

        t.setType("WITHDRAW");
        t.setAmount(amount);
        t.setDate(LocalDateTime.now());
        t.setAccount(acc);

        tr.save(t);

        return acc;
    }

    // Transfer
    public void transfer(

            String fromAccount,

            String toAccount,

            Double amount) {

        Account sender = ar.findByAccountNumber(fromAccount)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Sender Account Not Found"));

        Account receiver = ar.findByAccountNumber(toAccount)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Receiver Account Not Found"));

        if(sender.getBalance() < amount) {

            throw new RuntimeException(
                    "Insufficient Balance");
        }

        // Debit
        sender.setBalance(
                sender.getBalance() - amount);

        // Credit
        receiver.setBalance(
                receiver.getBalance() + amount);

        ar.save(sender);
        ar.save(receiver);

        // Sender Transaction
        Transaction debit = new Transaction();

        debit.setType("TRANSFER SENT");
        debit.setAmount(amount);
        debit.setDate(LocalDateTime.now());
        debit.setAccount(sender);

        tr.save(debit);

        // Receiver Transaction
        Transaction credit = new Transaction();

        credit.setType("TRANSFER RECEIVED");
        credit.setAmount(amount);
        credit.setDate(LocalDateTime.now());
        credit.setAccount(receiver);

        tr.save(credit);
    }

    // Show Transactions
    public List<Transaction> showTransactions() {

        return tr.findAll();
    }
}