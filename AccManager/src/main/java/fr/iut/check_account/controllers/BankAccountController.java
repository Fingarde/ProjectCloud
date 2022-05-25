package fr.iut.check_account.controllers;


import fr.iut.check_account.model.BankAccount;
import fr.iut.check_account.services.BankAccountService;
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
    public ResponseEntity<List<BankAccount>> getAllBankAcount() {
        return new ResponseEntity<>(serviceBank.getAllBankAccount(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<BankAccount> getOneBankAccountPerId(@PathVariable("id") long id) {
        return new ResponseEntity<>(serviceBank.getOneBankAccountPerId(id), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<BankAccount> getOneBankAccountPerName(@PathVariable("name") String name) {
        return new ResponseEntity<>(serviceBank.getOneBankAccountPerName(name), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<BankAccount> postBankAccount(@RequestBody BankAccount bankAccount) {
        serviceBank.addBankAccount(bankAccount);
        return new ResponseEntity<>(bankAccount, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteBankAccount(@PathVariable("id") long id) {
        BankAccount bankAccount = serviceBank.getOneBankAccountPerId(id);
        serviceBank.deleteBankAccount(bankAccount);
    }
}
