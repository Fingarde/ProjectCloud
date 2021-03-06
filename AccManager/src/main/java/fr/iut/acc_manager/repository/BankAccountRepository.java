package fr.iut.acc_manager.repository;

import fr.iut.acc_manager.exceptions.BankError;
import fr.iut.acc_manager.model.BankAccount;
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

    public BankAccount addBankAccount(BankAccount bankAccount) {
        try{
            ofy().save().entity(bankAccount).now();
            return bankAccount;
        }catch (Exception e) {
            throw new BankError("Unable to Add", HttpStatus.UNPROCESSABLE_ENTITY, e);
        }
    }

    public BankAccount deleteBankAccount(BankAccount bankAccount) {
        try{
            ofy().delete().entity(bankAccount).now();
            return bankAccount;
        }catch (Exception e) {
            throw new BankError("Unable to delete", HttpStatus.NOT_FOUND, e);
        }
    }

    public BankAccount getOneBankAccountPerId(long id) {
        try{
            BankAccount bankAccount = ofy().load().type(BankAccount.class).id(id).now();
            if (bankAccount == null) {
                throw new BankError("No entitiy found", HttpStatus.NOT_FOUND);
            }
            return bankAccount;
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

    public BankAccount modifyBankAccount(BankAccount bankAccount) {
        try{
            ofy().save().entity(bankAccount).now();
            return bankAccount;
        }catch (Exception e) {
            throw new BankError("Unable to Modify", HttpStatus.UNPROCESSABLE_ENTITY, e);
        }
    }

}
