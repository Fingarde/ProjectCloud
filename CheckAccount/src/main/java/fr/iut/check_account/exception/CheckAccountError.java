package fr.iut.check_account.exception;

import fr.iut.error.Error;
import org.springframework.http.HttpStatus;

public class CheckAccountError extends Error {
    public CheckAccountError(String message, HttpStatus status) {
        super(message, status);
    }

    public CheckAccountError(String message, HttpStatus status, Exception base) {
        super(message, status, base);
    }
}
