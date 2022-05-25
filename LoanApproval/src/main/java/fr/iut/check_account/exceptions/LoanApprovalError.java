package fr.iut.check_account.exceptions;

import fr.iut.error.Error;
import org.springframework.http.HttpStatus;

public class LoanApprovalError extends Error {
    public LoanApprovalError(String message, HttpStatus status) {
        super(message, status);
    }

    public LoanApprovalError(String message, HttpStatus status, Exception base) {
        super(message, status, base);
    }
}
