package geniemoviesandgames.model;

import geniemoviesandgames.model.item.Item;

import java.time.LocalDate;

public class Rental {
    public enum Status {
        EMPTY,
        BORROWED,
        RETURNED,
        OVERDUE
    }
    private int rentalID;
    private int rentalCount = 0;
    private Item item;
    private String customerID;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private Status userRentalStatus;
    private double totalPrice;

    public Rental() {
    }

    public Rental(int rentalID, int rentalCount, Item item, String customerID, LocalDate rentalDate, LocalDate returnDates, Status userRentalStatus, double totalPrice) {
        this.rentalID = rentalID;
        this.rentalCount = rentalCount;
        this.item = item;
        this.customerID = customerID;
        this.rentalDate = rentalDate;
        this.returnDate = returnDates;
        this.userRentalStatus = userRentalStatus;
        this.totalPrice = totalPrice;
    }

    public int getRentalID() {
        return rentalID;
    }
    public void setRentalID(int rentalID) {
        this.rentalID = rentalID;
    }

    public int getRentalCount() {
        return rentalCount;
    }

    public void setRentalCount(int rentalCount) {
        this.rentalCount = rentalCount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Status getUserRentalStatus() {
        return userRentalStatus;
    }

    public void setUserRentalStatus(Status userRentalStatus) {
        this.userRentalStatus = userRentalStatus;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rentalID=" + rentalID +
                ", rentalCount=" + rentalCount +
                ", item=" + item +
                ", customerID='" + customerID + '\'' +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", userRentalStatus=" + userRentalStatus +
                ", totalPrice=" + totalPrice +
                '}';
    }

}
