package geniemoviesandgames.model.Account;

public class AdminAccount extends Account {
    public AdminAccount(String username, String password) {
        super("admin", "Admin", "", "", username, password);
    }
}