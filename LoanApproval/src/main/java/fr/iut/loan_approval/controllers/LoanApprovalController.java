package fr.iut.loan_approval.controllers;


import fr.iut.loan_approval.model.LoanApproval;
import fr.iut.loan_approval.model.LoanResponse;
import fr.iut.loan_approval.services.LoanApprovalService;
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
    public ResponseEntity<LoanResponse> postLoanApproval(@RequestBody LoanApproval bankAccount) {
        LoanResponse loanResponse = serviceLoanApproval.addLoanApproval(bankAccount);
        return new ResponseEntity<>(loanResponse, HttpStatus.CREATED);
    }
}
