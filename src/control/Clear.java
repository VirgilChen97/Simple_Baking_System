package control;

import Entity.Account;

import java.util.ArrayList;

public class Clear {
    public static void clear(){
        AccountControl ac = new AccountControl();
        ArrayList<Account> a = AccountControl.getAccounts();
        for (int i = 0; i < a.size(); i++) {
            Account account = a.get(i);
            if(!account.getSuspended()) {
                account.setBalance(account.getBalance() + account.getUncleared_balance());
                account.setUncleared_balance(0.0);
            }
        }
        ac.saveData();
    }

    public static void main(String[] args) {
        new Clear().clear();
    }
}
