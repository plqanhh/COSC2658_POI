package geniemoviesandgames.model;

 public abstract class item {

    private String itemTitle;
    private String itemID;
    private int itemCopies;
    private double itemFees;
    private Boolean itemStatus;
    private Boolean item2DayLoan;

//     public item(String itemTitle, String itemID, int itemCopies, double itemFees, Boolean itemStatus, Boolean item2DayLoan) {
//         this.itemTitle = itemTitle;
//         this.itemID = itemID;
//         this.itemCopies = itemCopies;
//         this.itemFees = itemFees;
//         this.itemStatus = itemStatus;
//         this.item2DayLoan = item2DayLoan;
//     }




//     /**
//     * @return String return the itemTitle
//     */
    public String getItemTitle() {
        return itemTitle;
    }

//    /**
//     * @param itemTitle the itemTitle to set
//     */
    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }


//    /**
//     * @return String return the itemID
//     */
    public String getItemID() {
        return itemID;
    }

//    /**
//     * @param itemID the itemID to set
//     */
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }


//    /**
//     * @return int return the itemCopies
//     */
    public int getItemCopies() {
        return itemCopies;
    }

//    /**
//     * @param itemCopies the itemCopies to set
//     */
    public void setItemCopies(int itemCopies) {
        this.itemCopies = itemCopies;
    }

//    /**
//     * @return int return the itemFees
//     */
    public double getItemFees() {
        return itemFees;
    }

//    /**
//     * @param itemFees the itemFees to set
//     */
    public void setItemFees(double itemFees) {
        this.itemFees = itemFees;
    }


//    /**
//     * @return Boolean return the itemStatus
//     */
    public Boolean isItemStatus() {
        return itemStatus;
    }

//    /**
//     * @param itemStatus the itemStatus to set
//     */
    public void setItemStatus(Boolean itemStatus) {
        this.itemStatus = itemStatus;
    }


//    /**
//     * @return Boolean return the item2DayLoan
//     */
    public Boolean isItem2DayLoan() {
        return item2DayLoan;
    }

//    /**
////     * @param item2DayLoan the item2DayLoan to set
////     */
    public void setItem2DayLoan(Boolean item2DayLoan) {
        this.item2DayLoan = item2DayLoan;
    }

    public item(){
        setItemTitle(null);
        setItemID(null);
        setItemStatus(false);
        setItem2DayLoan(false);
        setItemCopies(0);
        setItemFees(0);
    }
    public  item(String itemID,String itemTitle ,Boolean item2DayLoan,int itemCopies,Double itemFees){


        setItemTitle(itemTitle);
        setItemID(itemID);
        setItemStatus(itemStatus);
        setItem2DayLoan(item2DayLoan);
        setItemCopies(itemCopies);
        setItemFees(itemFees);
    }

    public void borrowItem(){
        this.itemCopies = this.itemCopies + 1;
    }
    public void returnItem(){
        this.itemCopies = this.itemCopies -1;
        if(this.itemCopies ==0){
            itemStatus = false;
        }
    }
}
