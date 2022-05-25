package fr.iut.acc_manager.exceptions;

import fr.iut.error.Error;
import org.springframework.http.HttpStatus;

public class BankError extends Error {
    public BankError(String message, HttpStatus status) {
        super(message, status);
    }

    public BankError(String message, HttpStatus status, Exception base) {
        super(message, status, base);
    }
}
