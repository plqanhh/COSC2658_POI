package geniemoviesandgames.model.Rental;

import geniemoviesandgames.model.Item.Item;

public class Rental {
    private String rentalID;
    private Item item;

    public Rental(String rentalID, Item item) {
        this.rentalID = rentalID;
        this.item = item;
    }

    public String getRentalID() {
        return rentalID;
    }

    public void setRentalID(String rentalID) {
        this.rentalID = rentalID;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}