package geniemoviesandgames.model.product;



public class itemDVD extends item {

    public itemDVD() {
        super();
    }

    public itemDVD(String ID, String title, LoanType loanType, int stocks, Double fees, Genre genre) {
        super(ID, title, Media_Formats.DVD, loanType, stocks, fees, genre);
    }

}
