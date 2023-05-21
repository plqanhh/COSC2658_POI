package geniemoviesandgames.backend;

import geniemoviesandgames.model.account.Account;
import geniemoviesandgames.model.item.Item;

public class searchOption extends mainSystem{
    
    public static Item searchItemByID(String id) {
        for (Item i : listOfItems) {
            if ((i.getItemID()).equals(id)) {
                return i;
            }
        }
        return null;
    }

    public static Account searchAccountByID(String id) {
        for (Account a : listOfAccounts) {
            if ((a.getAccountID()).equals(id)) {
                return a;
            }
        }
        return null;
    }

    public static Item searchItemByName(String name) {
        for (Item i : listOfItems) {
            if ((i.getItemTitle()).equals(name)) {
                return i;
            }
        }
        return null;
    }

    public static Account searchAccountByName(String name) {
        for (Account a : listOfAccounts) {
            if ((a.getAccountFullname()).equals(name)) {
                return a;
            }
        }
        return null;
    }
}
