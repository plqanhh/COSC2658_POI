package geniemoviesandgames.controller;

import geniemoviesandgames.Switchingscene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import geniemoviesandgames.App;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class menuHomePageController implements Initializable {

    @FXML
    private AnchorPane menuHomePane;
    @FXML
    private BorderPane menuBorderPane;
    @FXML
    private AnchorPane myPane;

    public void switchToAccountInfo(ActionEvent event) throws IOException {
        // myPane = FXMLLoader.load(getClass().getResource("/screen_design/" + "accountInfo.fxml"));
        // menuBorderPane.setCenter(myPane);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       FXMLLoader loader = new FXMLLoader(getClass().getResource("/geniemoviesandgames/screen_design/menuBar.fxml"));
       try {
           AnchorPane myHomePane = loader.load();
           menuHomePane.getChildren().add(myHomePane);
           System.out.println("load done");
       } catch (IOException err) {
           err.printStackTrace();
       }
    }
}
