package geniemoviesandgames.model.account;

import java.util.ArrayList;

import geniemoviesandgames.model.item.item;

public class VipAccount extends account{
    private int freeRent=0;
    private int pointRewards=0;
    final int pointRewardsForfreeRent =100;
    final int pointRewardsEachReturn =10;
    
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
     * @return int return the pointRewards
     */
    public int getPointRewards() {
        return pointRewards;
    }

    /**
     * @param pointRewards the pointRewards to set
     */
    public void setPointRewards(int pointRewards) {
        this.pointRewards = pointRewards;
    }
    public VipAccount(String ID,String name, String address,int phone,ArrayList<item> rentals,String username,String password){
        super(ID, name, address, phone, rentals,LevelOfServices.VIP, username, password);
    }
}
