package geniemoviesandgames;
import java.io.IOException;

import javafx.fxml.FXML;



public class Switchingscene  {

    @FXML
    protected void switchToRegister() throws IOException {
        App.setRoot("register");
    }
    @FXML
    protected void switchToMenu() throws IOException{
        App.setRoot("menuHomePage");

    }
    @FXML
    protected void switchToLogin() throws IOException {
        App.setRoot("login");
    }
    @FXML
    protected void switchToNewUser() throws IOException {
        App.setRoot("newUser");
    }
    @FXML
    protected void switchToAdmin() throws IOException{
        App.setRoot("admin");
    }
//    @FXML
//    protected void switchToAccountInfo() throws IOException {
//        App.setScene("accountInfo");
//    }
}
