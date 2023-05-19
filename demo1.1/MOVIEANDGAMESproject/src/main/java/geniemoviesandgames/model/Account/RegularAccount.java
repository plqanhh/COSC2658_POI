package geniemoviesandgames.model.Account;

import geniemoviesandgames.model.Rental.Rental;

import java.util.List;

public class RegularAccount extends Account implements AccountPromotion{
    private final int ITEMS_TO_PROMOTE = 5;

    protected int itemReturned;


    public RegularAccount(String ID, String name, String address, String phone, String username, String password){
        super(ID, name, address, phone, username, password);
        this.itemReturned = 0;
    }

    public RegularAccount(String ID, String name, String address, String phone, String username, List<Rental> userRentals, String password, int itemReturned){
        super(ID, name, address, phone, userRentals, username, password);
        this.itemReturned = itemReturned;
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
            VipAccount vipAccount = new VipAccount(getUserID(), getUserName(), getUserAddress(),
                    getUserPhone(), getUsername(), getPassword());
            vipAccount.setUserRentals(getUserRentals());
            // Update any other properties if necessary
            vipAccount.setFreeRent(0);
            vipAccount.setPointRewards(0);

            System.out.println("Congratulations! Your account has been promoted to a Vip Account.");
        } else {
            System.out.println("You need to have returned at least " + ITEMS_TO_PROMOTE + " items to be eligible for promotion.");
        }
    }
}
