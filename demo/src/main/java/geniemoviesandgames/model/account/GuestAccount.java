package geniemoviesandgames.model.account;

import java.util.ArrayList;

import geniemoviesandgames.backend.AccountPromotion;
import geniemoviesandgames.model.item.Item;

public class GuestAccount extends Account {

    protected int itemBorrowAllow =2;
    protected int itemReturnedToPromote =3;
    
    public GuestAccount(String ID, String name, String address, int phone, ArrayList<Item> rentals, String username, String password){
        super(ID, name, address, phone, rentals,LevelOfServices.Guest, username, password);
    }

    public void CustomerBorrow(ArrayList<Item> itemsToBorrow) {
        if(itemsToBorrow.size() <= itemBorrowAllow){
            for (Item item : itemsToBorrow) {
                if (item.getItemStock() != 0 && item.getLoanType().equals("TWO_DAY")) {
                    item.borrowItem();
                    accountListOfRentals.add(item);
                    itemBorrow++;
                }
            }
        }
        else {
            System.out.println("Guest account only allows to rent maximum 2 items at a time !");
        }
    }

    public void CustomerReturn(Item itemIn) {
        itemIn.returnItem();
        itemIn.setItemStock(itemIn.getItemStock() + 1);
        itemReturned++;
        itemBorrow--;
        if(itemReturned==itemReturnedToPromote){
            AccountPromotion.promoteGuestToRegular(this);
        }
    }
}
