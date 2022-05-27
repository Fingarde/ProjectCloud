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

    @GetMapping("/{idAccount}")
    public ResponseEntity<Approval> getApprovalPerIdAccount(@PathVariable("idAccount") long idAccount) {
        return new ResponseEntity<>(serviceApproval.getApprovalPerIdAccount(idAccount), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Approval> postApproval(@RequestBody Approval approval) {
        serviceApproval.addApproval(approval);
        return new ResponseEntity<>(approval, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteApproval(@PathVariable("id") long id) {
        Approval approval = serviceApproval.getApprovalPerIdAccount(id);
        serviceApproval.deleteApproval(approval);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Approval> patchApproval(@PathVariable("id") long id, @RequestBody Approval approval) {
        Approval actualApproval = serviceApproval.getApprovalPerIdAccount(id);
        actualApproval.setResponse(approval.getResponse());
        serviceApproval.modifyBankAccount(actualApproval);
        return new ResponseEntity<>(actualApproval, HttpStatus.OK);
    }
}
