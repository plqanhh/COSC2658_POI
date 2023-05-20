package geniemoviesandgames.backend;

import java.time.LocalDate;

import geniemoviesandgames.model.product.item;
import geniemoviesandgames.model.product.item.LoanType;
import geniemoviesandgames.model.user.VipAccount;
import geniemoviesandgames.model.user.account;
import geniemoviesandgames.model.user.guestAccount;
import geniemoviesandgames.model.user.regularAccount;

public class ItemsReturning extends mainSystem {

    public double returnItemForGuest(guestAccount guestAccount, item item) {
        double bill;
        if (guestAccount.getListOfRentals().contains(item)) {
            item.returnItem();
            bill = guestAccount.accReturnItem(item);

            // Check if they return the items on time
            itemReturnCheck(guestAccount, item);

            // Check if guest customer is eligible for promotion to regular customer
            if (guestAccount.getItemReturned() >= 3) {
                guestAccount.setPromoteReady(true);
            }
            return bill;
        } else {
            System.out.println("Item " + item.getTitle() + " is not currently rented by the guest account.");
        }
        return 0;
    }

    public void returnItemForRegular(regularAccount regularAccount, item item) {
        if (regularAccount.getListOfRentals().contains(item)) {
            item.returnItem();
            regularAccount.getListOfRentals().remove(item);
            regularAccount.setItemReturned(regularAccount.getItemReturned() + 1);
            // Check if they return the items on time
            itemReturnCheck(regularAccount, item);
            // Check if regular customer is eligible for promotion to VIP customer
            if (regularAccount.getItemReturned() > 5) {
                regularAccount.setPromoteReady(true);
            }
        } else {
            System.out.println("Item " + item.getID() + " is not currently rented by the regular account.");
        }
    }

    //TODO: make returnItemForVip  
    public void returnItemForVIP(VipAccount vipAccount, item item) {
        if (vipAccount.getListOfRentals().contains(item)) {
            item.returnItem();
            vipAccount.getListOfRentals().remove(item);
            vipAccount.setItemReturned(vipAccount.getItemReturned() + 1);

            // Reward VIP customer with 10 points for each rental
            vipAccount.setPoints(vipAccount.getPoints() + 10);
            System.out.println("Congratulations ! You earned 10 reward points !");

            // Check if they return the items on time

            // Check if VIP customer is eligible for free rental
            if (vipAccount.getPoints() >= 100) {
                vipAccount.setPoints(vipAccount.getPoints() - 100);
                vipAccount.setFreeRent(vipAccount.getFreeRent() + 1);
                System.out.println("Congratulations! You have earned a free rental.");
            }
        } else {
            System.out.println("Item " + item.getID() + " is not currently rented by the VIP account.");
        }
    }

    public static Boolean itemReturnCheck(account accIn, item itemIn) {
        LocalDate today = LocalDate.now();
        LocalDate dayToReturn = accIn.getDate(itemIn);
        if (itemIn.getLoanType() == LoanType.ONE_WEEK) {
            dayToReturn.plusDays(7);
        } else if (itemIn.getLoanType() == LoanType.TWO_DAY) {
            dayToReturn.plusDays(2);
        }
        int compareValue = today.compareTo(dayToReturn);
        System.out.println(compareValue);
        if (compareValue > 0) {
            System.out.println("You are late");
            return false;
        } else if (compareValue < 0) {
            System.out.println("You are early");
            return true;
        } else {
            System.out.println("You are just on time");
            return true;
        }
    }
}
