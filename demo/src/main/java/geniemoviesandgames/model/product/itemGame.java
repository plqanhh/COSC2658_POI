package geniemoviesandgames.model.product;

public class itemGame extends item {

    public itemGame() {
        super();
    }

    public itemGame(String ID, String title, LoanType loanType, int stocks, Double fees) {
        super(ID, title, Media_Formats.Game, loanType, stocks, fees, null);
    }

}
