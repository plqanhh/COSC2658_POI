package geniemoviesandgames.backend;

import geniemoviesandgames.model.user.VipAccount;
import geniemoviesandgames.model.user.guestAccount;
import geniemoviesandgames.model.user.regularAccount;

public class promote {

    public static void promoteAcc(regularAccount accIn) {
        if (accIn.getPromoteReady() == true) {

            VipAccount v1 = new VipAccount(accIn.getID(), accIn.getFullname(), accIn.getAddress(),
                    accIn.getPhone(), accIn.getListOfRentals(), accIn.getListOfDates(), accIn.getUsername(),
                    accIn.getPassword());

            v1.setListOfRentals(accIn.getListOfRentals());
            v1.setItemReturned(0);
            v1.setFreeRent(0);
            v1.setPoints(0);
            mainSystem.updateAccount(accIn, v1);

            System.out.println("You have been promoted to a Vip");
        } else {
            System.out.println("You haven't fullfil the requirements");
        }
    }

    public static void promoteAcc(guestAccount accIn) {
        if (accIn.getPromoteReady() == true) {

            regularAccount r1 = new regularAccount(accIn.getID(), accIn.getFullname(), accIn.getAddress(),
                    accIn.getPhone(), accIn.getListOfRentals(), accIn.getListOfDates(), accIn.getUsername(),
                    accIn.getPassword());

            r1.setItemReturned(0);
            r1.setItemBorrow(0);
            r1.setListOfRentals(accIn.getListOfRentals());
            mainSystem.updateAccount(accIn, r1);
            
            System.out.println("You have been promoted to a regular");
        } else {
            System.out.println("You haven't fullfil the requirements");
        }

    }
}
