package fr.iut.app_manager.exceptions;

import fr.iut.error.Error;
import org.springframework.http.HttpStatus;

public class ApprovalError extends Error {
    public ApprovalError(String message, HttpStatus status) {
        super(message, status);
    }

    public ApprovalError(String message, HttpStatus status, Exception base) {
        super(message, status, base);
    }
}
