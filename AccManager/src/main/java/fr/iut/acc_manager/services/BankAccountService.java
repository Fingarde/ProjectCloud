package fr.iut.acc_manager.services;

import fr.iut.acc_manager.exceptions.BankError;
import fr.iut.acc_manager.model.BankAccount;
import fr.iut.acc_manager.repository.BankAccountRepository;
import fr.iut.acc_manager.verifiers.BankAccountVerifier;
import fr.iut.error.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public List<BankAccount> getAllBankAccount() {
        try {
            return bankAccountRepository.getAllBankAccount();
        } catch (Exception e) {
            if(e instanceof Error) {
                System.err.println(e.getMessage());
                if (((Error) e).getBase() != null) ((Error) e).getBase().printStackTrace();
                throw e;
            }

            e.printStackTrace();
            throw new BankError("Error fetching accounts", HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }

    public void addBankAccount(BankAccount bankAccount) {
        if (!BankAccountVerifier.verifyBankAccount(bankAccount)){
            throw new BankError("Error bank account is not valid", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            bankAccountRepository.addBankAccount(bankAccount);
        } catch (Exception e) {
            if(e instanceof Error) {
                System.err.println(e.getMessage());
                if (((Error) e).getBase() != null) ((Error) e).getBase().printStackTrace();
                throw e;
            }

            e.printStackTrace();
            throw new BankError("Error adding account", HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }

    public void deleteBankAccount(BankAccount bankAccount) {
        try {
            bankAccountRepository.deleteBankAccount(bankAccount);
        } catch (Exception e) {
            if(e instanceof Error) {
                System.err.println(e.getMessage());
                if (((Error) e).getBase() != null) ((Error) e).getBase().printStackTrace();
                throw e;
            }

            e.printStackTrace();
            throw new BankError("Error deleting account", HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }

    public BankAccount getOneBankAccountPerId(long id) {
        try {
            return bankAccountRepository.getOneBankAccountPerId(id);
        } catch (Exception e) {
            if(e instanceof Error) {
                System.err.println(e.getMessage());
                if (((Error) e).getBase() != null) ((Error) e).getBase().printStackTrace();
                throw e;
            }

            e.printStackTrace();
            throw new BankError("Error fetching account",  HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }

    public BankAccount getOneBankAccountPerName(String name) {
        try {
            return bankAccountRepository.getOneBankAccountPerName(name);
        } catch (Exception e) {
            if(e instanceof Error) {
                System.err.println(e.getMessage());
                if (((Error) e).getBase() != null) ((Error) e).getBase().printStackTrace();
                throw e;
            }

            e.printStackTrace();
            throw new BankError("Error fetching account",  HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }

    public void modifyBankAccount(BankAccount bankAccount) {
        if (!BankAccountVerifier.verifyBankAccount(bankAccount)){
            throw new BankError("Error bank account is not valid", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            bankAccountRepository.modifyBankAccount(bankAccount);
        } catch (Exception e) {
            if(e instanceof Error) {
                System.err.println(e.getMessage());
                if (((Error) e).getBase() != null) ((Error) e).getBase().printStackTrace();
                throw e;
            }

            e.printStackTrace();
            throw new BankError("Error modify account", HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }
}
