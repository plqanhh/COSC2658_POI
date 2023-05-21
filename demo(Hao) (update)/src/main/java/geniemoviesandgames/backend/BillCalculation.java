package geniemoviesandgames.backend;

import geniemoviesandgames.model.Rental;
import geniemoviesandgames.model.account.VipAccount;
import geniemoviesandgames.model.item.Item;
import geniemoviesandgames.model.account.Account;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class BillCalculation {
    public static double calculateTotalRentalFee(Account account) {
        double totalFee = 0.0;
        for (Rental rental : account.getAccountListOfRentals()) {
            totalFee += rental.getTotalPrice();
        }
        return totalFee;
    }

    public static void billPrinting(Account account) {
        System.out.println("Please return your items before due day or not you will be charged for additional fees !");
        System.out.println("Your cart:");
        for (Rental rental : account.getAccountListOfRentals()) {
            System.out.println("Rental no: " + rental.getRentalID());
            System.out.println("Item borrowed: " + rental.getItem().getItemTitle() + ", return date: " + rental.getReturnDate());
        }
        System.out.println("Your total rental fee is: $" + calculateTotalRentalFee(account));
    }

    public static boolean checkForLateReturn(Rental rental) {
        LocalDate actualReturnDay = LocalDate.now();

        LocalDate returnDay = rental.getReturnDate();
        long lateDays = ChronoUnit.DAYS.between(returnDay, actualReturnDay);

        return lateDays >= 1;
    }

    public static void billForLateReturn(Rental rental) {
        System.out.println("You have an item that is over return due !");
        System.out.println("You will have to pay a charged fee of: $" + calculateLateReturnFee(rental));
        // Set up rental status
        rental.setUserRentalStatus(Rental.Status.OVERDUE);
    }

    public static double calculateLateReturnFee(Rental rental) {
        LocalDate actualReturnDay = LocalDate.now();
        double chargedFees = 0.0;

        LocalDate returnDay = rental.getReturnDate();
        long lateDays = ChronoUnit.DAYS.between(returnDay, actualReturnDay);

        if (rental.getItem().getLoanType() == Item.LoanType.TWO_DAYS && lateDays >= 1) {
            // One day late is still counting, but 1/2 = 0, so we will do this
            if (lateDays == 1) {
                chargedFees += rental.getItem().getItemFees();
            }
            // Latedays >=2 will be calculated normally
            else {
                chargedFees += (double) lateDays / 2 * rental.getItem().getItemFees();
            }
        } else if (rental.getItem().getLoanType() == Item.LoanType.ONE_WEEK && lateDays >= 1) {
            // One day late is still counting, but 1/2 = 0, so we will do this
            if (lateDays == 1) {
                chargedFees += rental.getItem().getItemFees();
            }
            // Latedays >=2 will be calculated normally
            else {
                chargedFees += (double) lateDays / 7 * rental.getItem().getItemFees();
            }
        }
        return chargedFees;
    }
}