package geniemoviesandgames.model.item;

public class ItemGame extends Item {

    public ItemGame() {
        super();
    }

    public ItemGame(String ID, String title, LoanType loanType, int stocks, Double fees) {
        super(ID, title, Media_Formats.Game, loanType, stocks, fees, null);
    }

}
