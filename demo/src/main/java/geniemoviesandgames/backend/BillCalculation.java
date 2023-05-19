package geniemoviesandgames.backend;

import geniemoviesandgames.model.account.VipAccount;
import geniemoviesandgames.model.item.Item;
import geniemoviesandgames.model.account.Account;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class BillCalculation {
    public static double calculateTotalRentalFee(Account account) {
        double totalFee = 0.0;
        for (Item item : account.getAccountListOfRentals()) {
            totalFee += item.getItemFees();
        }
        return totalFee;
    }

    public static double calculateTotalRentalFeeForVIP(VipAccount vipaccount) {
        double totalFee = 0.0;
        for (Item item : vipaccount.getAccountListOfRentals()) {
            if (vipaccount.getFreeRentItems().contains(item)) {
                totalFee += 0;
            } else {
                totalFee += item.getItemFees();
            }
        }
        return totalFee;
    }

    public static void billPrinting(Account account) {
        System.out.println("Please return your items before due day or not you will be charged for additional fees !");
        for (Item item : account.getAccountListOfRentals()) {
            System.out.println("Item: " + item.getItemTitle() + ", return day: " + item.getReturnDay());
        }
        System.out.println("Your total rental fee is: $" + calculateTotalRentalFee(account));
    }

    public static void billPrintingForVip(VipAccount vipAccount) {
        System.out.println("Please return your items before due day or not you will be charged for additional fees !");
        for (Item item : vipAccount.getAccountListOfRentals()) {
            System.out.println("Item: " + item.getItemTitle() + ", return day: " + item.getReturnDay());
        }
        System.out.println("Your total rental fee is: $" + calculateTotalRentalFeeForVIP(vipAccount));
    }

    public static double calculateLateReturnFee(Account account) {
        LocalDate actualReturnDay = LocalDate.now();
        double chargedFees = 0.0;

        for (Item item : account.getAccountListOfRentals()) {
            LocalDate returnDay = item.getReturnDay();
            long lateDays = ChronoUnit.DAYS.between(returnDay, actualReturnDay);

            if (item.getLoanType() == Item.LoanType.TWO_DAYS && lateDays >= 2) {
                chargedFees += (double) lateDays / 2 * item.getItemFees();
            } else if (item.getLoanType() == Item.LoanType.ONE_WEEK && lateDays >= 7) {
                chargedFees += (double) lateDays / 7 * item.getItemFees();
            }
        }

        return chargedFees;
    }

    public static double calculateLateReturnItems(Account account) {
        LocalDate actualReturnDay = LocalDate.now();
        int chargedItemsCount = 0;

        for (Item item : account.getAccountListOfRentals()) {
            LocalDate returnDay = item.getReturnDay();
            long lateDays = ChronoUnit.DAYS.between(returnDay, actualReturnDay);

            if (item.getLoanType() == Item.LoanType.TWO_DAYS && lateDays >= 2) {
                chargedItemsCount++;
            } else if (item.getLoanType() == Item.LoanType.ONE_WEEK && lateDays >= 7) {
                chargedItemsCount++;
            }
        }

        return chargedItemsCount;
    }

    public static void checkForLateReturn(Account account) {
        if (calculateLateReturnItems(account) >= 1) {
            System.out.println("You have " + calculateLateReturnItems(account) + " item(s) that are over return day !");
            System.out.println("You will have to pay a charged fee of: $" + calculateLateReturnFee(account));
        }
    }
}