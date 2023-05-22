package geniemoviesandgames.controller;

import geniemoviesandgames.Switchingscene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class adminController extends Switchingscene implements Initializable {
    @FXML
    private AnchorPane adminMenuPane;

    @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/geniemoviesandgames/screen_design/adminMenuBar.fxml"));
        try {
            AnchorPane adminMenu = loader.load();
            adminMenuPane.getChildren().add(adminMenu);


        } catch (IOException err) {
            err.printStackTrace();
        }
    }
    
}
