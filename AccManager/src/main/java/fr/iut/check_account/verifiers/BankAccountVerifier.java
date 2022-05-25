package fr.iut.check_account.verifiers;

import fr.iut.check_account.model.BankAccount;

public class BankAccountVerifier {

    private static boolean isNullEmpty(String str) {
        return str == null || str.equals("");
    }

    public static boolean verifyBankAccount(BankAccount account) {
        return !isNullEmpty(account.getName()) && !isNullEmpty(account.getSurname()) && account.getRisk()!=null && account.getAccount() >= 0.0;
    }
}
