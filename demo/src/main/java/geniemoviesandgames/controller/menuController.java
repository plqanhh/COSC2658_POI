package geniemoviesandgames.controller;

import geniemoviesandgames.Switchingscence;
import geniemoviesandgames.model.user.account;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class menuController extends Switchingscence {
    protected static account mainAcc;

    public static void setMainAcc(account acc){
        mainAcc = acc;
    }
    private Text scary = new Text("super hello");

    
    public void initialize(){
        downPane.getChildren().add(scary);
    }
    @FXML private Pane downPane;
    @FXML private Text welcomeText;
    @FXML
    public void beginAction(){
        welcomeText.setText("hello");
        downPane.getChildren().clear();

    }
    
    
}
