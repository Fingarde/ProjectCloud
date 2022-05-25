package fr.iut.loan_approval.service;

import fr.iut.loan_approval.exception.UuidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CheckAccountService {
    public ResponseEntity<String> checkAccount(String uuid) {
        UUID id;
        try {
            id = UUID.fromString(uuid);
        } catch ( Exception e) {
            throw new UuidException(uuid);
        }
        return new ResponseEntity<>(id.toString(), HttpStatus.OK);
    }
}
