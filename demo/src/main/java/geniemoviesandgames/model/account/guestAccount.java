package geniemoviesandgames.model.account;

import java.util.ArrayList;

import geniemoviesandgames.model.item.item;

public class guestAccount extends account{

    protected int itemBorrowAllow =2;
    protected int itemReturnedToPromote =3;
    
    public guestAccount(String ID,String name, String address,int phone,ArrayList<item> rentals,String username,String password){
        super(ID, name, address, phone, rentals,LevelOfServices.Guest, username, password);
    }

}
