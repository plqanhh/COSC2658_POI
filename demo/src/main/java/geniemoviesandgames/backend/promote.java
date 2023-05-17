package geniemoviesandgames.backend;

import geniemoviesandgames.model.account.VipAccount;
import geniemoviesandgames.model.account.guestAccount;
import geniemoviesandgames.model.account.regularAccount;

public class promote extends mainSystem {

    public static void guestToRegular(guestAccount acc) {
        int a = listOfAccounts.indexOf(acc);
        listOfAccounts.remove(acc);
        regularAccount r1 = new regularAccount(acc.getAccountID(), acc.getAccountFullname(), acc.getAccountAddress(),
                acc.getAccountPhone(), acc.getAccountListOfRentals(), acc.getAccountUsername(),
                acc.getAccountPassword());
        listOfAccounts.add(a, r1);
    }

    public static void regularToVip(regularAccount acc) {
        int a = listOfAccounts.indexOf(acc);
        listOfAccounts.remove(acc);
        VipAccount v1 = new VipAccount(acc.getAccountID(), acc.getAccountFullname(), acc.getAccountAddress(),
                acc.getAccountPhone(), acc.getAccountListOfRentals(), acc.getAccountUsername(),
                acc.getAccountPassword());
        listOfAccounts.add(a, v1);
    }
}
