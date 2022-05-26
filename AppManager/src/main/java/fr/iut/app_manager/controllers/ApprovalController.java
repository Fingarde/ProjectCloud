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
    public ResponseEntity<Approval> getOneApprovalPerUuid(@PathVariable("uuid") String uuid) {
        return new ResponseEntity<>(serviceApproval.getOneApprovalPerUuid(uuid), HttpStatus.OK);
    }

    @GetMapping("/account/{idAccount}")
    public ResponseEntity<Approval> getApprovalPerIdAccount(@PathVariable("idAccount") long idAccount) {
        return new ResponseEntity<>(serviceApproval.getApprovalPerIdAccount(idAccount), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Approval> postApproval(@RequestBody Approval approval) {
        serviceApproval.addApproval(approval);
        return new ResponseEntity<>(approval, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public void deleteApproval(@PathVariable("uuid") String uuid) {
        Approval approval = serviceApproval.getOneApprovalPerUuid(uuid);
        serviceApproval.deleteApproval(approval);
    }
}
