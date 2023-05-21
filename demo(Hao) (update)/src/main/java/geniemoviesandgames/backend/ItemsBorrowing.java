package geniemoviesandgames.backend;

import geniemoviesandgames.model.Rental;
import geniemoviesandgames.model.account.Account;
import geniemoviesandgames.model.account.GuestAccount;
import geniemoviesandgames.model.account.RegularAccount;
import geniemoviesandgames.model.account.VipAccount;
import geniemoviesandgames.model.item.Item;

import java.time.LocalDate;
import java.util.ArrayList;

import static geniemoviesandgames.backend.BillCalculation.billPrinting;

public class ItemsBorrowing extends mainSystem {
    private void borrowItemsForGuest(GuestAccount guestAccount, ArrayList<Item> itemsToBorrow) {
        if (guestAccount.getItemBorrow() + itemsToBorrow.size() <= 2) {
            for (Item item : itemsToBorrow) {
                if (item.getLoanType() == Item.LoanType.TWO_DAYS) {
                    System.out.println("Guest account cannot borrow 2-day items.");
                    continue;
                }
                if (item.getItemStock() > 0) {
                    setUpRental(guestAccount, item);
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
                setUpRental(regularAccount, item);
            } else {
                System.out.println("Item " + item.getItemTitle() + " is out of stock.");
            }
        }
        billPrinting(regularAccount);
    }

    public void borrowItemsForVIP(VipAccount vipAccount, ArrayList<Item> itemsToBorrow) {
        for (Item item : itemsToBorrow) {
            if (item.getItemStock() > 0) {
                setUpRental(vipAccount, item);
            } else {
                System.out.println("Item " + item.getItemTitle() + " is out of stock.");
            }
        }
    }

    public void borrowItem(Item item) {
        item.setItemStock(item.getItemStock() - 1) ;
        if (item.getItemStock() == 0) {
            item.setStatus(Item.status.BORROWED);
        }
    }

    public void setUpRental(Account account, Item item) {
        // Set up attributes for rental class
        Rental rental = new Rental();
        rental.setRentalCount(rental.getRentalCount() + 1);
        rental.setRentalID(rental.getRentalCount());
        rental.setCustomerID(account.getAccountID());
        rental.setRentalDate(LocalDate.now());
        rental.setUserRentalStatus(Rental.Status.BORROWED);
        LocalDate rentDate = rental.getRentalDate();
        rental.setTotalPrice(item.getItemFees());

        borrowItem(item);
        // Add item into the rental
        rental.setItem(item);
        if (item.getLoanType() == Item.LoanType.TWO_DAYS) {
            rental.setReturnDate(rentDate.plusDays(2));
        }
        else {
            rental.setReturnDate(rentDate.plusDays(7));
        }
        // Increase items borrowed
        account.setItemBorrow(account.getItemBorrow() + 1);
        // Add the rental to the rental list of the account
        account.getAccountListOfRentals().add(rental);
    }

    // Function overload
    public void setUpRental(VipAccount vipAccount, Item item) {
        // Set up attributes for rental class
        Rental rental = new Rental();
        rental.setRentalCount(rental.getRentalCount() + 1);
        rental.setRentalID(rental.getRentalCount());
        rental.setCustomerID(vipAccount.getAccountID());
        rental.setRentalDate(LocalDate.now());
        rental.setUserRentalStatus(Rental.Status.BORROWED);
        LocalDate rentDate = rental.getRentalDate();
        rental.setTotalPrice(item.getItemFees());

        if (vipAccount.getFreeRent() >= 1) {
            borrowItem(item);
            // Add item into the rental
            rental.setItem(item);
            // Reset the price
            rental.setTotalPrice(0);
            // Set rent and return day
            if (item.getLoanType() == Item.LoanType.TWO_DAYS) {
                rental.setReturnDate(rentDate.plusDays(2));
            }
            else {
                rental.setReturnDate(rentDate.plusDays(7));
            }
            // Increase items borrowed
            vipAccount.setItemBorrow(vipAccount.getItemBorrow() + 1);
            // Add the rental to the rental list of the account
            vipAccount.getAccountListOfRentals().add(rental);
            // Minus 1 free rent
            vipAccount.setFreeRent(vipAccount.getFreeRent() - 1);
            System.out.println("Congratulations ! You just rented an item for free !");
            // Add the item to the free rent items list
            vipAccount.getFreeRentItems().add(item);
        }
        else {
            borrowItem(item);
            // Add item into the rental
            rental.setItem(item);
            // Set rent and return day
            if (item.getLoanType() == Item.LoanType.TWO_DAYS) {
                rental.setReturnDate(rentDate.plusDays(2));
            }
            else {
                rental.setReturnDate(rentDate.plusDays(7));
            }
            // Increase items borrowed
            vipAccount.setItemBorrow(vipAccount.getItemBorrow() + 1);
            // Add the rental to the rental list of the account
            vipAccount.getAccountListOfRentals().add(rental);
            // Increase reward points
            vipAccount.setPoints(vipAccount.getPoints() + 10);
        }
    }
}
