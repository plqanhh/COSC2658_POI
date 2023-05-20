package geniemoviesandgames.backend;

import geniemoviesandgames.model.product.item;
import geniemoviesandgames.model.user.account;

public class searchOption extends mainSystem{
    
    public static item searchItemByID(String id) {
        for (item i : listOfItems) {
            if ((i.getItemID()).equals(id)) {
                return i;
            }
        }
        return null;
    }

    public static account searchAccountByID(String id) {
        for (account a : listOfAccounts) {
            if ((a.getAccountID()).equals(id)) {
                return a;
            }
        }
        return null;
    }

    public static item searchItemByName(String name) {
        for (item i : listOfItems) {
            if ((i.getItemTitle()).equals(name)) {
                return i;
            }
        }
        return null;
    }

    public static account searchAccountByName(String name) {
        for (account a : listOfAccounts) {
            if ((a.getAccountFullname()).equals(name)) {
                return a;
            }
        }
        return null;
    }
}
