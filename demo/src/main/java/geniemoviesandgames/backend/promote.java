package geniemoviesandgames.backend;

import geniemoviesandgames.model.user.VipAccount;
import geniemoviesandgames.model.user.guestAccount;
import geniemoviesandgames.model.user.regularAccount;

public class promote extends mainSystem {

    public static void regularToVip(regularAccount acc) {
        int a = listOfAccounts.indexOf(acc);
        VipAccount v1 = new VipAccount(acc.getID(), acc.getFullname(), acc.getAddress(),
                acc.getPhone(), acc.getListOfRentals(),acc.getListOfDates(), acc.getUsername(),
                acc.getPassword());
        v1.setListOfRentals(acc.getListOfRentals());
        v1.setItemReturned(0);
        v1.setFreeRent(0);
        v1.setPoints(0);
        listOfAccounts.set(a, v1);
        System.out.println("You have been promoted to a Vip");
    }

    public static void guestToRegular(guestAccount acc) {
        int a = mainSystem.listOfAccounts.indexOf(acc);
        regularAccount r1 = new regularAccount(acc.getID(), acc.getFullname(), acc.getAddress(),
                acc.getPhone(), acc.getListOfRentals(),acc.getListOfDates(), acc.getUsername(),
                acc.getPassword());
        r1.setItemReturned(0);
        r1.setItemBorrow(0);
        r1.setListOfRentals(acc.getListOfRentals());
        listOfAccounts.set(a, r1);
        System.out.println("You have been promoted to a regular");
    }
}
