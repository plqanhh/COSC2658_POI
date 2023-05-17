package geniemoviesandgames.model.account;

import java.util.ArrayList;

import geniemoviesandgames.model.item.item;

public class regularAccount extends account{

    protected final int itemReturnedToPromote =5;    

    public regularAccount(String ID,String name, String address,int phone,ArrayList<item> rentals,String username,String password){
        super(ID, name, address, phone, rentals,LevelOfServices.Regular, username, password);
    }
    

}
