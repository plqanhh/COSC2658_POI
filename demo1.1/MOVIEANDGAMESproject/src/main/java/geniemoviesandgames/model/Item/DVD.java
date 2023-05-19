package geniemoviesandgames.model.Item;

public class DVD extends Item {
    public DVD(String itemID, String itemTitle, RentalType rentalType, LoanType loanType, int stock, double rentalFee, RentalStatus rentalStatus, Genre genre) {
        super(itemID, itemTitle, rentalType, loanType, stock, rentalFee, rentalStatus, genre);
        this.genre = genre;
    }
}