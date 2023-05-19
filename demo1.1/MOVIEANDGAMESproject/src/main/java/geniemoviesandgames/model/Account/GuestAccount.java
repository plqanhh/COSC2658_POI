package geniemoviesandgames.model.Account;

import geniemoviesandgames.model.Rental.Rental;

import java.util.List;

public class GuestAccount extends Account implements AccountPromotion{
    private final int MAX_ITEMS = 2;
    private final int ITEMS_TO_PROMOTE = 3;
    protected int itemBorrow;
    protected int itemReturned;

    public GuestAccount(String ID, String name, String address, String phone, String username, String password){
        super(ID, name, address, phone, username, password);
        itemBorrow = 0;
        itemReturned = 0;
    }

    public GuestAccount(String ID, String name, String address, String phone, List<Rental> rentals, String username, String password, int itemBorrow, int itemReturned){
        super(ID, name, address, phone, rentals, username, password);
        this.itemBorrow = itemBorrow;
        this.itemReturned = itemReturned;
    }
    

    public int getItemBorrow() {
        return this.itemBorrow;
    }

    public void setItemBorrow(int itemBorrow ) {
        this.itemBorrow = itemBorrow ;
    }

    public int getItemReturned() {
        return itemReturned;
    }

    public void setItemReturned(int itemReturned) {
        this.itemReturned = itemReturned;
    }

    @Override
    public void promoteAccount() {
        if (getItemReturned() >= ITEMS_TO_PROMOTE) {
            RegularAccount regularAccount = new RegularAccount(getUserID(), getUserName(), getUserAddress(),
                    getUserPhone(), getUsername(), getUserPassword());
            regularAccount.setItemReturned(getItemReturned());
            regularAccount.setUserRentals(getUserRentals());
            // Update any other properties if necessary
            regularAccount.setItemReturned(0);
            System.out.println("Congratulations! Your account has been promoted to a Regular Account.");
        } else {
            System.out.println("You need to have returned at least " + ITEMS_TO_PROMOTE + " items to be eligible for promotion.");
        }
    }
}
