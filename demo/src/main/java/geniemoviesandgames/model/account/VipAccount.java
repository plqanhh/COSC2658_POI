package geniemoviesandgames.model.account;

import java.util.ArrayList;

import geniemoviesandgames.model.item.Item;

public class VipAccount extends Account {
    protected int freeRent=0;
    protected int Points=0;
    protected final int PointsForfreeRent =100;
    protected int PointsEachReturn =10;
    protected ArrayList<Item> freeRentItems;
    
    /**
     * @return int return the freeRent
     */
    public int getFreeRent() {
        return freeRent;
    }

    /**
     * @param freeRent the freeRent to set
     */
    public void setFreeRent(int freeRent) {
        this.freeRent = freeRent;
    }

    /**
     * @return int return the Points
     */
    public int getPoints() {
        return Points;
    }

    /**
     * @param Points the Points to set
     */
    public void setPoints(int Points) {
        this.Points = Points;
    }

    public ArrayList<Item> getFreeRentItems() {
        return freeRentItems;
    }

    public void setFreeRentItems(ArrayList<Item> freeRentItems) {
        this.freeRentItems = freeRentItems;
    }

    public VipAccount(String ID, String name, String address, int phone, ArrayList<Item> rentals, String username, String password){
        super(ID, name, address, phone, rentals,LevelOfServices.VIP, username, password);
    }

    public void CustomerBorrow(Item itemIn) {
        if(freeRent>0){
            freeRent--;
        }
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
        Points+=PointsEachReturn;
        if(Points == PointsForfreeRent){
            Points=0;
            freeRent++;
        }
    }
}
