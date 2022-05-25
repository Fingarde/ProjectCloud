package fr.iut.check_account.repository;

import fr.iut.check_account.exceptions.BankError;
import fr.iut.check_account.model.BankAccount;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Repository
public class BankAccountRepository {

    public List<BankAccount> getAllBankAccount() {
        try{
            return ofy().load().type(BankAccount.class).list();
        }catch (Exception e) {
            throw new BankError("No entities found", HttpStatus.NOT_FOUND, e);
        }
    }

    public void addBankAccount(BankAccount bankAccount) {
        try{
            ofy().save().entity(bankAccount).now();
        }catch (Exception e) {
            throw new BankError("Unable to Add", HttpStatus.UNPROCESSABLE_ENTITY, e);
        }
    }

    public void deleteBankAccount(BankAccount bankAccount) {
        try{
            ofy().delete().entity(bankAccount).now();
        }catch (Exception e) {
            throw new BankError("Unable to delete", HttpStatus.NOT_FOUND, e);
        }
    }

    public BankAccount getOneBankAccountPerId(long id) {
        try{
            return ofy().load().type(BankAccount.class).id(id).now();
        }catch (Exception e) {
            throw new BankError("No entitiy found", HttpStatus.NOT_FOUND, e);
        }
    }

    public BankAccount getOneBankAccountPerName(String name) {
        try{
            return ofy().load().type(BankAccount.class).id(name).now();
        }catch (Exception e) {
            throw new BankError("No entitiy found", HttpStatus.NOT_FOUND, e);
        }
    }

}
