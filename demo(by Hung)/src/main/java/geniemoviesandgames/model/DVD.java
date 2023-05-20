package geniemoviesandgames.model;

public class DVD extends item{
    private String itemGenre;
    /**
     * @return String return the itemGenre
     */
    public String getItemGenre() {
        return itemGenre;
    }

    /**
     * @param itemGenre the itemGenre to set
     */
    public void setItemGenre(String itemGenre) {
        this.itemGenre = itemGenre;
    }


    public DVD(String itemGenre){
        super();
        setItemGenre(itemGenre);
    }

    public DVD(String ID,String title,Boolean DayLoan,int copies,double fees,String itemGenre){
        super(ID, title, DayLoan, copies, fees);
        setItemGenre(itemGenre);
    }


//    public DVD() {
//        super();
//        setItemGenre(null);
//    }
//    public void setItemGenre(String itemGenre) {
//        this.itemGenre = itemGenre;
//    }
//    public DVD(String ID,String title,Boolean DayLoan,int copies,double fees,String itemGenre) {
//        super(ID, title, DayLoan, copies, fees);
//        setItemGenre(itemGenre);
//    }
//    public DVD(String ID,String title,Boolean DayLoan,int copies,double fees,String genre){
//        super(ID, title, DayLoan, copies, fees, genre);
//    }
    
}
