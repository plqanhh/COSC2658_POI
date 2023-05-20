
package geniemoviesandgames.backend;

import geniemoviesandgames.model.product.item;
import geniemoviesandgames.model.product.item.LoanType;
import geniemoviesandgames.model.user.VipAccount;
import geniemoviesandgames.model.user.guestAccount;
import geniemoviesandgames.model.user.regularAccount;

public class ItemsBorrowing extends mainSystem {

    public static void borrowItems(guestAccount gAccount, item itemsToBorrow) {
        if (gAccount.getItemBorrow() + 1 <= 2) {
            if (itemsToBorrow.getLoanType() == LoanType.TWO_DAY) {
                System.out.println("Guest account cannot borrow 2-day items.");
            }
            if (itemsToBorrow.getStock() > 0) {
                itemsToBorrow.borrowItem();
                gAccount.accBorrowItem(itemsToBorrow);

            } else {
                System.out.println("Item " + itemsToBorrow.getTitle() + " is out of stock.");
            }
        } else {
            System.out.println("Guest account can only rent a maximum of 2 items at a time.");
        }
    }

    public static void borrowItems(regularAccount rAccount, item itemsToBorrow) {

        if (itemsToBorrow.getStock() > 0) {
            itemsToBorrow.borrowItem();
            rAccount.accBorrowItem(itemsToBorrow);

        } else {
            System.out.println("Item " + itemsToBorrow.getTitle() + " is out of stock.");
        }
    }

    public static void borrowItems(VipAccount vipAccount, item itemsToBorrow) {

        if (itemsToBorrow.getStock() > 0) {
            itemsToBorrow.borrowItem();
            vipAccount.accBorrowItem(itemsToBorrow);
        } else {
            System.out.println("Item " + itemsToBorrow.getID() + " is out of stock.");
        }
    }
}
