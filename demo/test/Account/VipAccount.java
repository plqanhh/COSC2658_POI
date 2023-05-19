package geniemoviesandgames.model.Account;

import geniemoviesandgames.model.Rental.Rental;

import java.util.List;

public class VipAccount extends Account {
    private final int POINTSTORENT = 100;
    private final int POINTSPERRETURN = 10;
    protected int freeRent;
    protected int points;

    public VipAccount(String ID, String name, String address, String phone, String username, String password){
        super(ID, name, address, phone, username, password);
        this.freeRent = 0;
        this.points = 0;
    }

    public VipAccount(String ID, String name, String address, String phone, String username, List<Rental> userRentals, String password, int freeRent, int points){
        super(ID, name, address, phone, userRentals, username, password);
        this.freeRent = freeRent;
        this.points = points;
    }

    public int getFreeRent() {
        return freeRent;
    }

    public void setFreeRent(int freeRent) {
        this.freeRent = freeRent;
    }

    public int getPointRewards() {
        return points;
    }

    public void setPointRewards(int pointRewards) {
        this.points = pointRewards;
    }
}
