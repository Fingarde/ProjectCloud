package fr.iut.loan_approval.exception;


import me.alidg.errors.annotation.ExceptionMapping;
import me.alidg.errors.annotation.ExposeAsArg;
import org.springframework.http.HttpStatus;

@ExceptionMapping(statusCode = HttpStatus.UNPROCESSABLE_ENTITY, errorCode = "uuid.parse")
public class UuidException extends RuntimeException {
    @ExposeAsArg(0)
    private final String uuid;

    public UuidException(final String uuid) {
        this.uuid = uuid;
    }
}
