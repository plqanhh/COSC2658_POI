package geniemoviesandgames.model.product;

abstract public class item {

    public enum Media_Formats {
        Record, DVD, Game
    }

    public enum LoanType {
        TWO_DAY, ONE_WEEK
    }

    public enum Status {
        BORROWED, AVAILABLE
    }

    public enum Genre {
        Action, Horror, Drama, Comedy
    }

    protected String title;
    protected String ID;
    protected int stock;
    protected double fees;
    protected LoanType loantype;
    protected Status status = Status.AVAILABLE;
    protected Genre genre;
    protected Media_Formats media;

    /**
     * @return String return the itemTitle
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param itemTitle the itemTitle to set
     */
    public void setTitle(String itemTitle) {
        this.title = itemTitle;
    }

    /**
     * @return String return the itemID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setID(String itemID) {
        this.ID = itemID;
    }

    /**
     * @return int return the itemCopies
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param itemCopies the itemCopies to set
     */
    public void setStock(int itemCopies) {
        this.stock = itemCopies;
    }

    /**
     * @return int return the itemFees
     */
    public double getFees() {
        return fees;
    }

    /**
     * @param itemFees the itemFees to set
     */
    public void setFees(double itemFees) {
        this.fees = itemFees;
    }

    public LoanType getLoantype() {
        return loantype;
    }

    public void setLoantype(LoanType loanType) {
        this.loantype = loanType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status itemStatus) {
        this.status = itemStatus;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Media_Formats getMedia() {
        return media;
    }

    public void setMedia(Media_Formats media) {
        this.media = media;
    }

    public item() {
        setTitle(null);
        setID(null);
        setStock(0);
        setFees(0);
    }

    public item(String ID, String title, Media_Formats media, LoanType loanType, int stocks, Double fees, Genre genre) {
        setTitle(title);
        setID(ID);
        if (stocks > 0) {
            setStatus(Status.AVAILABLE);
        } else {
            setStatus(Status.BORROWED);
        }
        setMedia(media);
        setStock(stocks);
        setFees(fees);
        setLoantype(loanType);
        setGenre(genre);
    }

    public void borrowItem() {
        this.stock = this.stock -1 ;
        if (this.stock == 0) {
            setStatus(Status.BORROWED);
        }
    }

    public void returnItem() {
        this.stock = this.stock + 1;
        if (this.stock != 0) {
            setStatus(Status.AVAILABLE);
        }
    }
}
