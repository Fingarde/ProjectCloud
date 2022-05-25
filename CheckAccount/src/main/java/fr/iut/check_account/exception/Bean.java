package fr.iut.check_account.exception;

import me.alidg.errors.HttpError;
import me.alidg.errors.adapter.HttpErrorAttributesAdapter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class Bean implements HttpErrorAttributesAdapter {

    @Override
    public Map<String, Object> adapt(HttpError httpError) {
        return Collections.singletonMap("Errors", httpError.getErrors().stream().map((HttpError.CodedMessage::getCode)));
    }
}