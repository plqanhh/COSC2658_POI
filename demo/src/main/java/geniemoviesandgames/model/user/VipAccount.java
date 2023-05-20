package geniemoviesandgames.model.user;

import java.time.LocalDate;
import java.util.ArrayList;

import geniemoviesandgames.model.product.item;

public class VipAccount extends account{
    protected int freeRent=0;
    protected int Points=0;
    protected final int PointsForfreeRent =100;
    protected final int PointsEachReturn =10;
    
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
    public VipAccount(String ID,String name, String address,String phone,ArrayList<item> rentals,ArrayList<LocalDate> date,String username,String password){
        super(ID, name, address, phone, rentals,date,LevelOfServices.VIP, username, password);
    }


}
