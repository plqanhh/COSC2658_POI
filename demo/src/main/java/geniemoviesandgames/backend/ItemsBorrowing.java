package geniemoviesandgames.backend;

import java.time.LocalDate;
import java.util.ArrayList;

import geniemoviesandgames.model.product.item;
import geniemoviesandgames.model.product.item.LoanType;
import geniemoviesandgames.model.user.VipAccount;
import geniemoviesandgames.model.user.guestAccount;
import geniemoviesandgames.model.user.regularAccount;

import static geniemoviesandgames.backend.BillCalculation.billPrinting;
import static geniemoviesandgames.backend.BillCalculation.billPrintingForVip;

public class ItemsBorrowing extends mainSystem {

    public static void borrowItems(guestAccount gAccount, ArrayList<item> itemsToBorrow) {
        if (gAccount.getItemBorrow() + itemsToBorrow.size() <= 2) {
            for (item i : itemsToBorrow) {
                if (i.getLoanType() == LoanType.TWO_DAY) {
                    System.out.println("Guest account cannot borrow 2-day items.");
                    continue;
                }
                if (i.getItemStock() > 0) {
                    i.borrowItem();
                    gAccount.addAccountListOfRentals(i);
                    gAccount.setItemBorrow(gAccount.getItemBorrow() + 1);
                    // Set up rental and return day
                    gAccount.addAccountListOfRentalsDates(LocalDate.now());
                    gAccount.addBillToPay(i.getItemFees());

                } else {
                    System.out.println("Item " + i.getItemTitle() + " is out of stock.");
                }
            }
        } else {
            System.out.println("Guest account can only rent a maximum of 2 items at a time.");
        }
    }

    public static void borrowItems(guestAccount gAccount, item itemsToBorrow) {
        if (gAccount.getItemBorrow() + 1 <= 2) {
            if (itemsToBorrow.getLoanType() == LoanType.TWO_DAY) {
                System.out.println("Guest account cannot borrow 2-day items.");
            }
            if (itemsToBorrow.getItemStock() > 0) {
                itemsToBorrow.borrowItem();
                gAccount.addAccountListOfRentals(itemsToBorrow);
                gAccount.setItemBorrow(gAccount.getItemBorrow() + 1);
                // Set up rental and return day
                gAccount.addAccountListOfRentalsDates(LocalDate.now());
                gAccount.addBillToPay(itemsToBorrow.getItemFees());

            } else {
                System.out.println("Item " + itemsToBorrow.getItemTitle() + " is out of stock.");
            }
        } else {
            System.out.println("Guest account can only rent a maximum of 2 items at a time.");
        }
    }

    public static void borrowItems(regularAccount rAccount, ArrayList<item> itemsToBorrow) {
        for (item i : itemsToBorrow) {
            if (i.getItemStock() > 0) {
                i.borrowItem();
                rAccount.addAccountListOfRentals(i);
                rAccount.setItemBorrow(rAccount.getItemBorrow() + 1);
                // Set up rental and return day
                rAccount.addAccountListOfRentalsDates(LocalDate.now());
                rAccount.addBillToPay(i.getItemFees());
            } else {
                System.out.println("Item " + i.getItemTitle() + " is out of stock.");
            }
        }
    }

    public static void borrowItems(regularAccount rAccount, item itemsToBorrow) {

        if (itemsToBorrow.getItemStock() > 0) {
            itemsToBorrow.borrowItem();
            rAccount.addAccountListOfRentals(itemsToBorrow);
            rAccount.setItemBorrow(rAccount.getItemBorrow() + 1);
            // Set up rental and return day
            rAccount.addAccountListOfRentalsDates(LocalDate.now());
            rAccount.addBillToPay(itemsToBorrow.getItemFees());

        } else {
            System.out.println("Item " + itemsToBorrow.getItemTitle() + " is out of stock.");
        }
    }

    public static void borrowItems(VipAccount vipAccount, ArrayList<item> itemsToBorrow) {
        for (item i : itemsToBorrow) {
            if (i.getItemStock() > 0) {
                if (vipAccount.getFreeRent() >= 1) {
                    i.borrowItem();
                    vipAccount.addAccountListOfRentals(i);
                    vipAccount.setItemBorrow(vipAccount.getItemBorrow() + 1);
                    vipAccount.setFreeRent(vipAccount.getFreeRent() - 1);
                    System.out.println("Congratulations ! You just rented an item for free !");
                    // Set up rental and return day
                    i.setUpReturnDay();
                } else {
                    i.borrowItem();
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
