package geniemoviesandgames.model;

public class game extends item{


    public game(){
        super();
    }
    public game(String ID,String title,Boolean item2DayLoan,int itemCopies,double itemFees){
        super(ID, title, item2DayLoan, itemCopies, itemFees);
    }


}
