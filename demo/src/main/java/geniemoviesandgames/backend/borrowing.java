
package geniemoviesandgames.backend;

import geniemoviesandgames.model.product.item;
import geniemoviesandgames.model.product.item.LoanType;
import geniemoviesandgames.model.user.VipAccount;
import geniemoviesandgames.model.user.guestAccount;
import geniemoviesandgames.model.user.regularAccount;

public class borrowing {

    public borrowing(guestAccount accIn, item itemsToBorrow) {
        guestAccount accOut = accIn;
        if (accOut.getItemBorrow() + 1 <= 2) {
            if (itemsToBorrow.getLoanType() == LoanType.TWO_DAY) {
                System.out.println("Guest account cannot borrow 2-day items.");
            }
            if (itemsToBorrow.getStock() > 0) {
                itemsToBorrow.borrowItem();
                accOut.accBorrowItem(itemsToBorrow);
                mainSystem.updateAccount(accIn, accOut);
            } else {
                System.out.println("Item " + itemsToBorrow.getTitle() + " is out of stock.");
            }
        } else {
            System.out.println("Guest account can only rent a maximum of 2 items at a time.");
        }
    }

    public borrowing(regularAccount accIn, item itemsToBorrow) {
        regularAccount accOut = accIn;
        if (itemsToBorrow.getStock() > 0) {
            itemsToBorrow.borrowItem();
            accOut.accBorrowItem(itemsToBorrow);
            mainSystem.updateAccount(accIn, accOut);
        } else {
            System.out.println("Item " + itemsToBorrow.getID() + " is out of stock.");
        }
    }

    public borrowing(VipAccount accIn, item itemsToBorrow) {
        VipAccount accOut = accIn;
        if (itemsToBorrow.getStock() > 0) {
            itemsToBorrow.borrowItem();
            accOut.accBorrowItem(itemsToBorrow);
            mainSystem.updateAccount(accIn, accOut);
        } else {
            System.out.println("Item " + itemsToBorrow.getID() + " is out of stock.");
        }
    }
    
}
