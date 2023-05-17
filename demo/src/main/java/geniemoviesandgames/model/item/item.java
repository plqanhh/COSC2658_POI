package geniemoviesandgames.model.item;

abstract public class item {

    public enum Media_Formats {
        Record, DVD, Game
    }

    public enum LoanType {
        TWO_DAY, ONE_WEEK
    }

    public enum RentalStatus {
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
    protected RentalStatus itemRentalStatus = RentalStatus.AVAILABLE;
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

    public RentalStatus getRentalStatus() {
        return itemRentalStatus;
    }

    public void setRentalStatus(RentalStatus rentalStatus) {
        this.itemRentalStatus = rentalStatus;
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

    public item() {
        setItemTitle(null);
        setItemID(null);
        setItemStock(0);
        setItemFees(0);
    }

    public item(String ID, String title, Media_Formats media, LoanType loanType, int stocks, Double fees, Genre genre) {
        setItemTitle(title);
        setItemID(ID);
        if (stocks > 0) {
            setRentalStatus(RentalStatus.AVAILABLE);
        } else {
            setRentalStatus(RentalStatus.BORROWED);
        }
        setItemStock(stocks);
        setItemFees(fees);
        setLoanType(loanType);
        setItemGenre(genre);
    }

    public void borrowItem() {
        this.itemStock = this.itemStock + 1;
    }

    public void returnItem() {
        this.itemStock = this.itemStock - 1;
        if (this.itemStock == 0) {
            setRentalStatus(RentalStatus.BORROWED);
        }
    }
}
