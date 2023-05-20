package geniemoviesandgames.backend;

import geniemoviesandgames.model.user.VipAccount;
import geniemoviesandgames.model.user.guestAccount;
import geniemoviesandgames.model.user.regularAccount;

public class promote extends mainSystem {

    public static void regularToVip(regularAccount acc) {
        int a = listOfAccounts.indexOf(acc);
        listOfAccounts.remove(acc);
        VipAccount v1 = new VipAccount(acc.getAccountID(), acc.getAccountFullname(), acc.getAccountAddress(),
                acc.getAccountPhone(), acc.getAccountListOfRentals(),acc.getAccountListOfRentalsDates(), acc.getAccountUsername(),
                acc.getAccountPassword());
        v1.setAccountListOfRentals(acc.getAccountListOfRentals());
        v1.setItemReturned(0);
        v1.setFreeRent(0);
        v1.setPoints(0);
        listOfAccounts.add(a, v1);
        System.out.println("You have been promoted to a Vip");
    }

    public static void guestToRegular(guestAccount acc) {
        int a = mainSystem.listOfAccounts.indexOf(acc);
        listOfAccounts.remove(acc);
        regularAccount r1 = new regularAccount(acc.getAccountID(), acc.getAccountFullname(), acc.getAccountAddress(),
                acc.getAccountPhone(), acc.getAccountListOfRentals(),acc.getAccountListOfRentalsDates(), acc.getAccountUsername(),
                acc.getAccountPassword());
        r1.setItemReturned(0);
        r1.setItemBorrow(0);
        r1.setAccountListOfRentals(acc.getAccountListOfRentals());
        listOfAccounts.add(a, r1);
        System.out.println("You have been promoted to a regular");
    }
}
