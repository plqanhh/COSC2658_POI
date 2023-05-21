package geniemoviesandgames.model.account;

import java.util.ArrayList;

import geniemoviesandgames.model.Rental;

public class RegularAccount extends Account {

    protected final int itemReturnedToPromote =5;    

    public RegularAccount(String ID, String name, String address, int phone, ArrayList<Rental> rentals, String username, String password){
        super(ID, name, address, phone, rentals,LevelOfServices.Regular, username, password);
    }
}
