package geniemoviesandgames.model.Account;

import geniemoviesandgames.model.Rental.Rental;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    private String userID;
    private String userName;
    private String userAddress;
    private String userPhone;
    private List<Rental> userRentals;
    private String username;
    private String password;

    protected Account(String userID, String userName, String userAddress, String userPhone,
                   String username, String password) {
        this.userID = userID;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.userRentals = new ArrayList<>();
        this.username = username;
        this.password = password;
    }

    protected Account(String userID, String userName, String userAddress, String userPhone,
                      List<Rental> userRentals, String username, String password) {
        this.userID = userID;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.userRentals = userRentals;
        this.username = username;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public List<Rental> getUserRentals() {
        return userRentals;
    }

    public void setUserRentals(List<Rental> userRentals) {
        this.userRentals = userRentals;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return password;
    }

    public void setUserPassword(String password) {
        this.password = password;
    }
}
