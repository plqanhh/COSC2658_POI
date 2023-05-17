package geniemoviesandgames.model.item;

public class itemGame extends item {

    public itemGame() {
        super();
    }

    public itemGame(String ID, String title, Media_Formats media, LoanType loanType, int stocks, Double fees, Genre genre) {
        super(ID, title, media, loanType, stocks, fees, null);
    }

}
