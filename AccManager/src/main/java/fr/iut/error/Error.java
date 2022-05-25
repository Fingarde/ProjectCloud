package fr.iut.acc_manager.exceptions;

import org.springframework.http.HttpStatus;

public class Error extends RuntimeException {
    private HttpStatus status;
    private String message;

    public Error(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
