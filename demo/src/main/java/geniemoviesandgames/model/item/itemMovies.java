package geniemoviesandgames.model.item;

public class itemMovies extends item {

    public itemMovies() {
        super();
    }

    public itemMovies(String ID, String title, Media_Formats media, LoanType loanType, int stocks, Double fees, Genre genre) {
        super(ID, title, media, loanType, stocks, fees, genre);
    }
}
