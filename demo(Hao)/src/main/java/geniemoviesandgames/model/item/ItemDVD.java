package geniemoviesandgames.model.item;



public class ItemDVD extends Item {

    public ItemDVD() {
        super();
    }

    public ItemDVD(String ID, String title, LoanType loanType, int stocks, Double fees, Genre genre) {
        super(ID, title, Media_Formats.DVD, loanType, stocks, fees, genre);
    }

}
