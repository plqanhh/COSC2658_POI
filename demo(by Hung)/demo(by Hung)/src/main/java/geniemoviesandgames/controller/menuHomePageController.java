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

public class menuHomePageController extends Switchingscene implements Initializable {

    @FXML
    private AnchorPane menuHomePane;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/geniemoviesandgames/screen_design/menuBar.fxml"));
        try {
            AnchorPane menuPane = loader.load();
            menuHomePane.getChildren().add(menuPane);

        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    }



