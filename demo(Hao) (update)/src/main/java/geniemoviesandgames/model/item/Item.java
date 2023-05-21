package geniemoviesandgames.model.item;

import java.time.LocalDate;

abstract public class Item {

    public enum Media_Formats {
        Record, DVD, Game
    }

    public enum LoanType {
        TWO_DAYS, ONE_WEEK
    }


    public enum status {
        BORROWED, AVAILABLE
    }

    public enum Genre {
        Action, Horror, Drama, Comedy
    }

    protected String itemTitle;
    protected String itemID;
    protected int itemStock;
    protected double itemFees;
    protected LoanType itemLoanType;
    protected status itemStatus = status.AVAILABLE;
    protected Genre itemGenre;
    protected Media_Formats itemMedia;

    /**
     * @return String return the itemTitle
     */
    public String getItemTitle() {
        return itemTitle;
    }

    /**
     * @param itemTitle the itemTitle to set
     */
    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    /**
     * @return String return the itemID
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    /**
     * @return int return the itemCopies
     */
    public int getItemStock() {
        return itemStock;
    }

    /**
     * @param itemCopies the itemCopies to set
     */
    public void setItemStock(int itemCopies) {
        this.itemStock = itemCopies;
    }

    /**
     * @return int return the itemFees
     */
    public double getItemFees() {
        return itemFees;
    }

    /**
     * @param itemFees the itemFees to set
     */
    public void setItemFees(double itemFees) {
        this.itemFees = itemFees;
    }

    /*
     * public RentalType getRentalType() {
     * return item;
     * }
     * 
     * public void setRentalType(RentalType rentalType) {
     * this.item = rentalType;
     * }
     */
    public LoanType getLoanType() {
        return itemLoanType;
    }

    public void setLoanType(LoanType loanType) {
        this.itemLoanType = loanType;
    }

    public status getStatus() {
        return itemStatus;
    }

    public void setStatus(status itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Genre getItemGenre() {
        return itemGenre;
    }

    public void setItemGenre(Genre genre) {
        this.itemGenre = genre;
    }

    public Media_Formats getItemMedia() {
        return itemMedia;
    }

    public void setItemMedia(Media_Formats media) {
        this.itemMedia = media;
    }

    public Item() {
        setItemTitle(null);
        setItemID(null);
        setItemStock(0);
        setItemFees(0);
    }

    public Item(String ID, String title, Media_Formats media, LoanType loanType, int stocks, Double fees, Genre genre) {
        setItemTitle(title);
        setItemID(ID);
        if (stocks > 0) {
            setStatus(status.AVAILABLE);
        } else {
            setStatus(status.BORROWED);
        }
        setItemMedia(media);
        setItemStock(stocks);
        setItemFees(fees);
        setLoanType(loanType);
        setItemGenre(genre);
    }

}
