package fr.iut.loan_approval.controller;

import fr.iut.loan_approval.Couille;
import fr.iut.loan_approval.service.CheckAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@ControllerAdvice
public class CheckAccountController {


    @Autowired
    private CheckAccountService service;

    @GetMapping("/check")
    public ResponseEntity<String> check(@RequestParam String uuid) {

            return service.checkAccount(uuid);

    }

    @GetMapping("/e")
    public String couille(@RequestBody @Valid Couille couille) {
        return couille.getCouilleGauche();

    }

}
