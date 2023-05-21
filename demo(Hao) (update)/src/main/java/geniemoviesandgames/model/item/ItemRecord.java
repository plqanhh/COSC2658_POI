package geniemoviesandgames.model.item;

public class ItemRecord extends Item {

    public ItemRecord() {
        super();
    }

    public ItemRecord(String ID, String title, LoanType loanType, int stocks, Double fees, Genre genre) {
        super(ID, title, Media_Formats.Record, loanType, stocks, fees, genre);
    }
}
