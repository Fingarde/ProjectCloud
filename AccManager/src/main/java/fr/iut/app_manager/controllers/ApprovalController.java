package fr.iut.app_manager.controllers;

import fr.iut.app_manager.model.Approval;
import fr.iut.app_manager.services.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/approval")
public class ApprovalController {
    @Autowired
    private ApprovalService serviceApproval;

    @GetMapping("")
    public ResponseEntity<List<Approval>> getAllApproval() {
        return new ResponseEntity<>(serviceApproval.getAllApproval(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Approval> getOneBankAccountPerUuid(@PathVariable("uuid") String uuid) {
        return new ResponseEntity<>(serviceApproval.getOnBankAccountPerUuid(uuid), HttpStatus.OK);
    }

    @GetMapping("/{idAccount}")
    public ResponseEntity<Approval> getOneBankAccountPerIdAccount(@PathVariable("idAccount") long idAccount) {
        return new ResponseEntity<>(serviceApproval.getOnBankAccountPerIdAccount(idAccount), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Approval> postBankAccount(@RequestBody Approval approval) {
        serviceApproval.addApproval(approval);
        return new ResponseEntity<>(approval, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public void deleteBankAccount(@PathVariable("uuid") String uuid) {
        Approval approval = serviceApproval.getOnBankAccountPerUuid(uuid);
        serviceApproval.deleteApproval(approval);
    }
}
