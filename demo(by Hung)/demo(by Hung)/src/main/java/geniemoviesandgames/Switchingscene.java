package geniemoviesandgames;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;



public class Switchingscene  {



    @FXML
    protected void switchToRegister() throws IOException {
        App.setRoot("register");
    }
    @FXML
    protected void switchToMenu() throws IOException{
        App.setRoot("menuHomePage");
        App.isRole = false;

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
        App.isRole = true;
    }
    @FXML
    protected void switchToAccountInfo() throws IOException {
        App.setRoot("accountInfo");
    }
    @FXML
    protected void switchToListOfOrder() throws IOException{
        App.setRoot("listOfOrder");
    }
    @FXML
    protected void switchToGameList() throws IOException {
        App.setRoot("game");
    }
    @FXML
    protected void switchToDVDList() throws IOException {
        App.setRoot("DVD");
    }
    @FXML
    protected void switchToOldMovieRecordList() throws IOException {
        App.setRoot("oldMovieRecord");

    }
    @FXML
    protected void switchToOutOfStockList() throws IOException {
        App.setRoot("outOfStock");
    }


}
