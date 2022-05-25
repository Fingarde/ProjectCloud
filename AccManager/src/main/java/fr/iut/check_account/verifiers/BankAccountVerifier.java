package fr.iut.loan_approval.verifiers;

import fr.iut.loan_approval.model.BankAccount;

public class BankAccountVerifier {

    private static boolean isNullEmpty(String str) {
        return str == null || str.equals("");
    }

    public static boolean verifyBankAccount(BankAccount account) {
        return !isNullEmpty(account.getName()) && !isNullEmpty(account.getSurname()) && account.getRisk()!=null && account.getAccount() >= 0.0;
    }
}
