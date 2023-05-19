package geniemoviesandgames.model.Item;

public class Item {
    protected enum RentalType {
        RECORD, DVD, GAME
    }

    protected enum LoanType {
        TWO_DAY, ONE_WEEK
    }

    protected enum RentalStatus {
        BORROWED, AVAILABLE
    }

    protected enum Genre {
        ACTION, HORROR, DRAMA, COMEDY
    }
    protected String itemID;
    protected String itemTitle;
    protected RentalType rentalType;
    protected LoanType loanType;
    protected int stock;
    protected double rentalFee;
    protected RentalStatus rentalStatus;
    protected Genre genre;

    public Item(String itemID, String itemTitle, RentalType rentalType, LoanType loanType, int stock, double rentalFee, RentalStatus rentalStatus, Genre genre) {
        this.itemID = itemID;
        this.itemTitle = itemTitle;
        this.rentalType = rentalType;
        this.loanType = loanType;
        this.stock = stock;
        this.rentalFee = rentalFee;
        this.rentalStatus = rentalStatus;
        this.genre = genre;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public int getStock() {
        return stock;
    }

    public RentalType getRentalType() {
        return rentalType;
    }

    public void setRentalType(RentalType rentalType) {
        this.rentalType = rentalType;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }
}
