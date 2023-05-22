package geniemoviesandgames.model;

public class movies extends item{
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


    public movies(String itemGenre){
        super();
        setItemGenre(itemGenre);
    }
    public movies(String ID,String title,Boolean DayLoan,int copies,double fees,String itemGenre){
        super(ID, title, DayLoan, copies, fees);
        setItemGenre(itemGenre);
    }
}
