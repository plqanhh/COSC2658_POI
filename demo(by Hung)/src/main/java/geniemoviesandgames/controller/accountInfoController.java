package geniemoviesandgames.controller;

import geniemoviesandgames.Switchingscene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class accountInfoController extends Switchingscene implements Initializable {
    @FXML
    private AnchorPane menuPaneForAccountInfo;
    @FXML
    private BorderPane menuBorderPane;
    @FXML
    private AnchorPane myPane;



//@FXML
// private void switchToAccountInfo(ActionEvent) throws IOException{
//    myPane = FXMLLoader.load(getClass().getResource("screen_design/" + "accountInfo.fxml"));
//    menuBorderPane.setCenter(myPane);
//}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/geniemoviesandgames/screen_design/menuBar.fxml"));
        try {
            AnchorPane menuPane = loader.load();
            menuPaneForAccountInfo.getChildren().add(menuPane);

        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}