package fr.iut.error;

import org.springframework.http.HttpStatus;

public class Error extends RuntimeException {
    private HttpStatus status;
    private String message;
    private Exception base;

    public Error(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public Error(String message, HttpStatus status, Exception base) {
        super(message);
        this.status = status;
        this.message = message;
        this.base = base;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Exception getBase() {
        return base;
    }
}
