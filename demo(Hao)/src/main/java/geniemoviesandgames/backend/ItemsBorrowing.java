package geniemoviesandgames.backend;

import geniemoviesandgames.model.account.GuestAccount;
import geniemoviesandgames.model.account.RegularAccount;
import geniemoviesandgames.model.account.VipAccount;
import geniemoviesandgames.model.item.Item;

import java.util.ArrayList;

import static geniemoviesandgames.backend.BillCalculation.billPrinting;
import static geniemoviesandgames.backend.BillCalculation.billPrintingForVip;

public class ItemsBorrowing extends mainSystem {
    public void borrowItemsForGuest(GuestAccount guestAccount, ArrayList<Item> itemsToBorrow) {
        if (guestAccount.getItemBorrow() + itemsToBorrow.size() <= 2) {
            for (Item item : itemsToBorrow) {
                if (item.getLoanType() == Item.LoanType.TWO_DAYS) {
                    System.out.println("Guest account cannot borrow 2-day items.");
                    continue;
                }
                if (item.getItemStock() > 0) {
                    item.borrowItem();
                    guestAccount.getAccountListOfRentals().add(item);
                    guestAccount.setItemBorrow(guestAccount.getItemBorrow() + 1);
                    // Set up rental and return day
                    item.setUpReturnDay();
                } else {
                    System.out.println("Item " + item.getItemTitle() + " is out of stock.");
                }
            }
            billPrinting(guestAccount);
        } else {
            System.out.println("Guest account can only rent a maximum of 2 items at a time.");
        }
    }

    public void borrowItemsForRegular(RegularAccount regularAccount, ArrayList<Item> itemsToBorrow) {
        for (Item item : itemsToBorrow) {
            if (item.getItemStock() > 0) {
                item.borrowItem();
                regularAccount.getAccountListOfRentals().add(item);
                regularAccount.setItemBorrow(regularAccount.getItemBorrow() + 1);
                // Set up rental and return day
                item.setUpReturnDay();
            } else {
                System.out.println("Item " + item.getItemTitle() + " is out of stock.");
            }
        }
        billPrinting(regularAccount);
    }

    public void borrowItemsForVIP(VipAccount vipAccount, ArrayList<Item> itemsToBorrow) {
        for (Item item : itemsToBorrow) {
            if (item.getItemStock() > 0) {
                if (vipAccount.getFreeRent() >= 1) {
                    item.borrowItem();
                    vipAccount.getAccountListOfRentals().add(item);
                    vipAccount.setItemBorrow(vipAccount.getItemBorrow() + 1);
                    vipAccount.setFreeRent(vipAccount.getFreeRent() - 1);
                    System.out.println("Congratulations ! You just rented an item for free !");
                    vipAccount.getFreeRentItems().add(item);
                    // Set up rental and return day
                    item.setUpReturnDay();
                }
                else {
                    item.borrowItem();
                    vipAccount.getAccountListOfRentals().add(item);
                    vipAccount.setItemBorrow(vipAccount.getItemBorrow() + 1);
                    // Set up rental and return day
                    item.setUpReturnDay();
                }
            } else {
                System.out.println("Item " + item.getItemID() + " is out of stock.");
            }
        }
        billPrintingForVip(vipAccount);
    }
}
