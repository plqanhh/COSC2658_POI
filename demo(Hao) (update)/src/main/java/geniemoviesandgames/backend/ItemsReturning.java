package geniemoviesandgames.backend;

import geniemoviesandgames.model.Rental;
import geniemoviesandgames.model.account.GuestAccount;
import geniemoviesandgames.model.account.RegularAccount;
import geniemoviesandgames.model.account.VipAccount;
import geniemoviesandgames.model.item.Item;

import static geniemoviesandgames.backend.AccountPromotion.promoteGuestToRegular;
import static geniemoviesandgames.backend.AccountPromotion.promoteRegularToVip;
import static geniemoviesandgames.backend.BillCalculation.billForLateReturn;
import static geniemoviesandgames.backend.BillCalculation.checkForLateReturn;

public class ItemsReturning extends mainSystem {
    public void returnItems(GuestAccount guestAccount, Rental rental) {
        // Check if the rental is rented
        if (guestAccount.getAccountListOfRentals().contains(rental)) {
            // Check if they return the items on time
            if (checkForLateReturn(rental)) {
                billForLateReturn(rental);
                returnItem(rental.getItem());
                guestAccount.setItemReturned(guestAccount.getItemReturned() + 1);
            }
            else {
                returnItem(rental.getItem());
                guestAccount.setItemReturned(guestAccount.getItemReturned() + 1);
                rental.setUserRentalStatus(Rental.Status.RETURNED);
            }

            // Check if guest customer is eligible for promotion to regular customer
            if (guestAccount.getItemReturned() >= 3) {
                promoteGuestToRegular(guestAccount);
            }
        } else {
            System.out.println("Rental no: " + rental.getRentalID() + " is not currently rented by the guest account.");
        }
    }

    // Function overload
    public void returnItems(RegularAccount regularAccount, Rental rental) {
        // Check if the rental is rented
        if (regularAccount.getAccountListOfRentals().contains(rental)) {
            // Check if they return the items on time
            if (checkForLateReturn(rental)) {
                billForLateReturn(rental);
                returnItem(rental.getItem());
                regularAccount.setItemReturned(regularAccount.getItemReturned() + 1);
            }
            else {
                returnItem(rental.getItem());
                regularAccount.setItemReturned(regularAccount.getItemReturned() + 1);
                rental.setUserRentalStatus(Rental.Status.RETURNED);
            }

            // Check if regular customer is eligible for promotion to VIP customer
            if (regularAccount.getItemReturned() > 5) {
                promoteRegularToVip(regularAccount);
            }
        } else {
            System.out.println("Rental no: " + rental.getRentalID() + " is not currently rented by the regular account.");
        }
    }

    // Function overload
        public void returnItems(VipAccount vipAccount, Rental rental) {
        // Check if the rental is rented
        if (vipAccount.getAccountListOfRentals().contains(rental)) {
            // Check if they return the items on time
            if (checkForLateReturn(rental)) {
                billForLateReturn(rental);
                returnItem(rental.getItem());
                vipAccount.setItemReturned(vipAccount.getItemReturned() + 1);
            }
            else {
                returnItem(rental.getItem());
                vipAccount.setItemReturned(vipAccount.getItemReturned() + 1);
                rental.setUserRentalStatus(Rental.Status.RETURNED);
            }

            // Check if VIP customer is eligible for free rental
            if (vipAccount.getPoints() >= 100) {
                vipAccount.setPoints(vipAccount.getPoints() - 100);
                vipAccount.setFreeRent(vipAccount.getFreeRent() + 1);
                System.out.println("Congratulations! You have earned a free rental.");
            }
        } else {
            System.out.println("Rental no: " + rental.getRentalID() + " is not currently rented by the VIP account.");
        }
    }

    public void returnItem(Item item) {
        item.setItemStock(item.getItemStock() + 1) ;
        if (item.getItemStock() > 0) {
            item.setStatus(Item.status.AVAILABLE);
        }
    }
}
