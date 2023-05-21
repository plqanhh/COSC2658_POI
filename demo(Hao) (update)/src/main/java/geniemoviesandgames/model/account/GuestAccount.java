package geniemoviesandgames.model.account;

import java.util.ArrayList;

import geniemoviesandgames.backend.AccountPromotion;
import geniemoviesandgames.model.Rental;
import geniemoviesandgames.model.item.Item;

public class GuestAccount extends Account {

    protected int itemBorrowAllow =2;
    protected int itemReturnedToPromote =3;
    
    public GuestAccount(String ID, String name, String address, int phone, ArrayList<Rental> rentals, String username, String password){
        super(ID, name, address, phone, rentals,LevelOfServices.Guest, username, password);
    }
}
