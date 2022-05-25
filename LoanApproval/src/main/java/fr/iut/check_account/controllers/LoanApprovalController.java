package fr.iut.check_account.controllers;


import fr.iut.check_account.model.LoanApproval;
import fr.iut.check_account.services.LoanApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loanapproval")
public class LoanApprovalController {

    @Autowired
    private LoanApprovalService serviceLoanApproval;

    @PostMapping("")
    public ResponseEntity<LoanApproval> postLoanApproval(@RequestBody LoanApproval bankAccount) {
        serviceLoanApproval.addLoanApproval(bankAccount);
        return new ResponseEntity<>(bankAccount, HttpStatus.CREATED);
    }
}
