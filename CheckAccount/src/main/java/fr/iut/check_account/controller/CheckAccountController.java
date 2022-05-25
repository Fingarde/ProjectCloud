package fr.iut.check_account.controller;

import fr.iut.check_account.model.Risk;
import fr.iut.check_account.service.CheckAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/checkaccount")
public class CheckAccountController {

    @Autowired
    private CheckAccountService service;

    @GetMapping("/{id}")
    public ResponseEntity<Risk> getRisk(@PathVariable("id") long id) {
        return new ResponseEntity<>(service.getRisk(id), HttpStatus.OK);
    }

}
