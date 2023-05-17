package geniemoviesandgames.model.item;

public class itemDVD extends item {

    public itemDVD() {
        super();
    }

    public itemDVD(String ID, String title, Media_Formats media, LoanType loanType, int stocks, Double fees, Genre genre) {
        super(ID, title, media, loanType, stocks, fees, genre);
    }

}
