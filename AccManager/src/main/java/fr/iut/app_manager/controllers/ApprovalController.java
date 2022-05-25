package fr.iut.loan_approval.controllers;

import fr.iut.loan_approval.model.Approval;
import fr.iut.loan_approval.services.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/approval")
public class ApprovalController {
    @Autowired
    private ApprovalService serviceApproval;

    @GetMapping("")
    public ResponseEntity<List<Approval>> getAllApproval() {
        try {
            return new ResponseEntity<>(serviceApproval.getAllApproval(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Approval> getOneBankAccount(@PathVariable("uuid") String uuid) {
        try {
            return new ResponseEntity<>(serviceApproval.getOnBankAccount(uuid), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Approval> postBankAccount(@RequestBody @Valid Approval approval) {
        try {
            serviceApproval.addApproval(approval);
            return new ResponseEntity<>(approval, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/{uuid}")
    public void deleteBankAccount(@PathVariable("uuid") String uuid) {
        Approval approval = serviceApproval.getOnBankAccount(uuid);
        serviceApproval.deleteApproval(approval);
    }
}
