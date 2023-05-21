package geniemoviesandgames.backend;

import java.util.ArrayList;

import geniemoviesandgames.model.product.item;
import geniemoviesandgames.model.user.account;

public class displayAll {

    public static ArrayList<item> displayItem(){
        return mainSystem.getListOfItems(); 
    }
    public static ArrayList<account> displayAccount(){
        return mainSystem.getListOfAccounts();
    }
    public static ArrayList<item> displayItemSortByName(){
        ArrayList<item> sortedList = new ArrayList<>(mainSystem.getListOfAccounts().size());
        return sortedList;
    }
}
