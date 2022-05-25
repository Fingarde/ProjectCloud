package fr.iut.check_account.service;

import fr.iut.check_account.exception.CheckAccountError;
import fr.iut.check_account.model.BankAccount;
import fr.iut.check_account.model.Risk;
import fr.iut.config.Config;
import fr.iut.error.Error;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CheckAccountService {

    public Risk getRisk(long id) {
        try {
            RestTemplateBuilder builder = new RestTemplateBuilder();
            var template = builder.build();
            BankAccount bankAccount = template.getForObject(Config.ACC_MANAGER_URL+id, BankAccount.class);
            return bankAccount.getRisk();
        } catch (Exception e) {
            if(e instanceof Error) {
                System.err.println(e.getMessage());
                if (((Error) e).getBase() != null) ((Error) e).getBase().printStackTrace();
                throw e;
            }

            e.printStackTrace();
            throw new CheckAccountError("Error fetching account",  HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }
}
