package geniemoviesandgames.model.product;

public class itemRecord extends item {

    public itemRecord() {
        super();
    }

    public itemRecord(String ID, String title, LoanType loanType, int stocks, Double fees, Genre genre) {
        super(ID, title, Media_Formats.Record, loanType, stocks, fees, genre);
    }
}
