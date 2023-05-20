package geniemoviesandgames.backend;

import static geniemoviesandgames.backend.BillCalculation.checkForLateReturn;

import geniemoviesandgames.model.product.item;
import geniemoviesandgames.model.user.VipAccount;
import geniemoviesandgames.model.user.guestAccount;
import geniemoviesandgames.model.user.regularAccount;

public class ItemsReturning extends mainSystem {
    public void returnItemForGuest(guestAccount guestAccount, item item) {
        if (guestAccount.getAccountListOfRentals().contains(item)) {
            item.returnItem();
            guestAccount.getAccountListOfRentals().remove(item);
            guestAccount.setItemReturned(guestAccount.getItemReturned() + 1);
            // Check if they return the items on time
            checkForLateReturn(guestAccount);

            // Check if guest customer is eligible for promotion to regular customer
            if (guestAccount.getItemReturned() >= 3) {
                promote.guestToRegular(guestAccount);
            }
        } else {
            System.out.println("Item " + item.getItemTitle() + " is not currently rented by the guest account.");
        }
    }

    public void returnItemForRegular(regularAccount regularAccount, item item) {
        if (regularAccount.getAccountListOfRentals().contains(item)) {
            item.returnItem();
            regularAccount.getAccountListOfRentals().remove(item);
            regularAccount.setItemReturned(regularAccount.getItemReturned() + 1);
            // Check if they return the items on time
            checkForLateReturn(regularAccount);

            // Check if regular customer is eligible for promotion to VIP customer
            if (regularAccount.getItemReturned() > 5) {
                promote.regularToVip(regularAccount);
            }
        } else {
            System.out.println("Item " + item.getItemID() + " is not currently rented by the regular account.");
        }
    }

    public void returnItemForVIP(VipAccount vipAccount, item item) {
        if (vipAccount.getAccountListOfRentals().contains(item)) {
            item.returnItem();
            vipAccount.getAccountListOfRentals().remove(item);
            vipAccount.setItemReturned(vipAccount.getItemReturned() + 1);

            // Reward VIP customer with 10 points for each rental
            vipAccount.setPoints(vipAccount.getPoints() + 10);
            System.out.println("Congratulations ! You earned 10 reward points !");

            // Check if they return the items on time
            checkForLateReturn(vipAccount);

            // Check if VIP customer is eligible for free rental
            if (vipAccount.getPoints() >= 100) {
                vipAccount.setPoints(vipAccount.getPoints() - 100);
                vipAccount.setFreeRent(vipAccount.getFreeRent() + 1);
                System.out.println("Congratulations! You have earned a free rental.");
            }
        } else {
            System.out.println("Item " + item.getItemID() + " is not currently rented by the VIP account.");
        }
    }
}
