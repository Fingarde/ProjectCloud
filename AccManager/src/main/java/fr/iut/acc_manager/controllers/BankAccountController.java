package fr.iut.acc_manager.controllers;


import fr.iut.acc_manager.model.BankAccount;
import fr.iut.acc_manager.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankaccount")
public class BankAccountController {

    @Autowired
    private BankAccountService serviceBank;

    @GetMapping("")
    public ResponseEntity<List<BankAccount>> getAllBankAccount() {
        return new ResponseEntity<>(serviceBank.getAllBankAccount(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getOneBankAccountPerId(@PathVariable("id") long id) {
        return new ResponseEntity<>(serviceBank.getOneBankAccountPerId(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<BankAccount> postBankAccount(@RequestBody BankAccount bankAccount) {
        serviceBank.addBankAccount(bankAccount);
        return new ResponseEntity<>(bankAccount, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BankAccount> deleteBankAccount(@PathVariable("id") long id) {
        BankAccount bankAccount = serviceBank.getOneBankAccountPerId(id);
        serviceBank.deleteBankAccount(bankAccount);
        return new ResponseEntity<>(bankAccount, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> putAccount(@PathVariable("id") long id, @RequestBody BankAccount bankAccount) {
        BankAccount actualAccount = serviceBank.getOneBankAccountPerId(id);
        if (bankAccount.getName()!=null) actualAccount.setName(bankAccount.getName());
        if (bankAccount.getSurname()!=null) actualAccount.setSurname(bankAccount.getSurname());
        actualAccount.setAccount(bankAccount.getAccount());
        if (bankAccount.getRisk()!=null) actualAccount.setRisk(bankAccount.getRisk());
        serviceBank.modifyBankAccount(actualAccount);
        return new ResponseEntity<>(actualAccount, HttpStatus.OK);
    }

}
