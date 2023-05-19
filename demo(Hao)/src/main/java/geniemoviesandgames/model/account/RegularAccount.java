package geniemoviesandgames.model.account;

import java.util.ArrayList;

import geniemoviesandgames.backend.AccountPromotion;
import geniemoviesandgames.model.item.Item;

public class RegularAccount extends Account {

    protected final int itemReturnedToPromote =5;    

    public RegularAccount(String ID, String name, String address, int phone, ArrayList<Item> rentals, String username, String password){
        super(ID, name, address, phone, rentals,LevelOfServices.Regular, username, password);
    }
    
    public void CustomerBorrow(Item itemIn) {
        itemIn.borrowItem();
        if (itemIn.getItemStock() != 0) {
            itemIn.setItemStock(itemIn.getItemStock() - 1);
            accountListOfRentals.add(itemIn);
            itemBorrow++;
        }
    }

    public void CustomerReturn(Item itemIn) {
        itemIn.returnItem();
        itemIn.setItemStock(itemIn.getItemStock() + 1);
        itemReturned++;
        itemBorrow--;
        if(itemReturned==itemReturnedToPromote){
            AccountPromotion.promoteRegularToVip(this);
        }
    }
}
