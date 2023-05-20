package geniemoviesandgames.model.user;

import java.time.LocalDate;
import java.util.ArrayList;

import geniemoviesandgames.backend.promote;
import geniemoviesandgames.model.product.item;

public class regularAccount extends account {

    protected final int itemReturnedToPromote = 5;

    public regularAccount(String ID, String name, String address, String phone, ArrayList<item> rentals,
            ArrayList<LocalDate> date, String username, String password) {
        super(ID, name, address, phone, rentals, date, LevelOfServices.Regular, username, password);
    }

    public void CustomerBorrow(item itemIn) {
        itemIn.borrowItem();
        if (itemIn.getItemStock() != 0) {
            itemIn.setItemStock(itemIn.getItemStock() - 1);
            accountListOfRentals.add(itemIn);
            itemBorrow++;
        }
    }

    public void CustomerReturn(item itemIn) {
        itemIn.returnItem();
        itemIn.setItemStock(itemIn.getItemStock() + 1);
        itemReturned++;
        itemBorrow--;
        if (itemReturned == itemReturnedToPromote) {
            promote.regularToVip(this);
        }
    }
}
