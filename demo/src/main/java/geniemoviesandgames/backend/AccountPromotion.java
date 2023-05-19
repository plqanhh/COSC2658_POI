package geniemoviesandgames.backend;

import geniemoviesandgames.model.account.VipAccount;
import geniemoviesandgames.model.account.GuestAccount;
import geniemoviesandgames.model.account.RegularAccount;

public class AccountPromotion extends mainSystem {

    public static void promoteRegularToVip(RegularAccount acc) {
        int a = mainSystem.listOfAccounts.indexOf(acc);
        VipAccount v1 = new VipAccount(acc.getAccountID(), acc.getAccountFullname(), acc.getAccountAddress(),
                acc.getAccountPhone(), acc.getAccountListOfRentals(), acc.getAccountUsername(),
                acc.getAccountPassword());
        v1.setAccountListOfRentals(acc.getAccountListOfRentals());
        v1.setItemReturned(0);
        v1.setItemReturned(0);
        v1.setFreeRent(0);
        v1.setPoints(0);

        // Remove the regular account from the list of accounts and add the new VIP account
        listOfAccounts.remove(acc);
        listOfAccounts.add(a, v1);
        System.out.println("Regular account " + acc.getAccountID() + " has been promoted to a VIP account.");
    }

    public static void promoteGuestToRegular(GuestAccount acc) {
        int a = mainSystem.listOfAccounts.indexOf(acc);
        RegularAccount r1 = new RegularAccount(acc.getAccountID(), acc.getAccountFullname(), acc.getAccountAddress(),
                acc.getAccountPhone(), acc.getAccountListOfRentals(), acc.getAccountUsername(),
                acc.getAccountPassword());
        r1.setItemReturned(0);
        r1.setItemBorrow(0);
        r1.setAccountListOfRentals(acc.getAccountListOfRentals());

        // Remove the guest account from the list of accounts and add the new regular account
        listOfAccounts.remove(acc);
        listOfAccounts.add(a, r1);
        System.out.println("Guest account " + acc.getAccountID() + " has been promoted to a regular account.");
    }
}
