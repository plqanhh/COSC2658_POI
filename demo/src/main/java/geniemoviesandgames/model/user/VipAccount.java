package geniemoviesandgames.model.user;

import java.util.ArrayList;

import geniemoviesandgames.model.product.item;

public class VipAccount extends account{
    protected int freeRent=0;
    protected int Points=0;
    protected final int PointsForfreeRent =100;
    protected int PointsEachReturn =10;
    
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
    public VipAccount(String ID,String name, String address,String phone,ArrayList<item> rentals,String username,String password){
        super(ID, name, address, phone, rentals,LevelOfServices.VIP, username, password);
    }

    public void CustomerBorrow(item itemIn) {
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

    public void CustomerReturn(item itemIn) {
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
