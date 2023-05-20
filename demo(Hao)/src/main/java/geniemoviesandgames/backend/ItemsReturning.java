package geniemoviesandgames.backend;

import geniemoviesandgames.model.account.GuestAccount;
import geniemoviesandgames.model.account.RegularAccount;
import geniemoviesandgames.model.account.VipAccount;
import geniemoviesandgames.model.item.Item;

import static geniemoviesandgames.backend.AccountPromotion.promoteGuestToRegular;
import static geniemoviesandgames.backend.AccountPromotion.promoteRegularToVip;
import static geniemoviesandgames.backend.BillCalculation.checkForLateReturn;

public class ItemsReturning extends mainSystem {
    public void returnItemForGuest(GuestAccount guestAccount, Item item) {
        if (guestAccount.getAccountListOfRentals().contains(item)) {
            item.returnItem();
            guestAccount.ac().remove(item);
            guestAccount.setItemReturned(guestAccount.getItemReturned() + 1);
            // Check if they return the items on time
            checkForLateReturn(guestAccount);

            // Check if guest customer is eligible for promotion to regular customer
            if (guestAccount.getItemReturned() >= 3) {
                promoteGuestToRegular(guestAccount);
            }
        } else {
            System.out.println("Item " + item.getItemTitle() + " is not currently rented by the guest account.");
        }
    }

    public void returnItemForRegular(RegularAccount regularAccount, Item item) {
        if (regularAccount.getAccountListOfRentals().contains(item)) {
            item.returnItem();
            regularAccount.getAccountListOfRentals().remove(item);
            regularAccount.setItemReturned(regularAccount.getItemReturned() + 1);
            // Check if they return the items on time
            checkForLateReturn(regularAccount);

            // Check if regular customer is eligible for promotion to VIP customer
            if (regularAccount.getItemReturned() > 5) {
                promoteRegularToVip(regularAccount);
            }
        } else {
            System.out.println("Item " + item.getItemID() + " is not currently rented by the regular account.");
        }
    }

    public void returnItemForVIP(VipAccount vipAccount, Item item) {
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
