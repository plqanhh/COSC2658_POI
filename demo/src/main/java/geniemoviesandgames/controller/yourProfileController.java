package geniemoviesandgames.controller;

import java.io.IOException;

import geniemoviesandgames.Switchingscence;
import geniemoviesandgames.model.user.account;
import geniemoviesandgames.model.user.guestAccount;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class yourProfileController extends Switchingscence {

    protected static account mainAcc;
    public int clickTime = 0;

    public static void setMainAcc(account acc) {
        mainAcc = acc;
    }

    public static account getMainAcc() {
        return mainAcc;
    }

    @FXML
    private TextField nameTextfield;
    @FXML
    private TextField addTextfield;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField usernameTextfield;
    @FXML
    private TextField passwordTextfield;
    @FXML
    private Text warningText;
    @FXML
    private Button goBackButton;

    @FXML
    public void beginEdit() {
        nameTextfield.setEditable(true);
        addTextfield.setEditable(true);
        phoneTextField.setEditable(true);
        usernameTextfield.setEditable(true);
        passwordTextfield.setEditable(true);
    }

    @FXML
    public void goBackAction() throws IOException {
        if (clickTime == 1) {
            warningText.setText("Are You Sure");
        } else {
            switch(mainAcc.getLevelOfServices()){
                case Guest:
                guestAccount g1 = new guestAccount(mainAcc.getID(), STYLESHEET_CASPIAN, STYLESHEET_CASPIAN, STYLESHEET_CASPIAN, null, null, STYLESHEET_MODENA, STYLESHEET_CASPIAN);
            }
            switchToMenu();
        }
    }

    public void initialize() {
        nameTextfield.setText(mainAcc.getFullname());
        addTextfield.setText(mainAcc.getAddress());
        phoneTextField.setText(mainAcc.getPhone());
        usernameTextfield.setText(mainAcc.getUsername());
        passwordTextfield.setText(mainAcc.getPassword());
        goBackButton.setOnMouseClicked(e -> {if(editCheck(nameTextfield.getText(), addTextfield.getText(), phoneTextField.getText(), usernameTextfield.getText(), passwordTextfield.getText()));
            clickTime++;
        });
        warningText.setText("");
    }

    public Boolean editCheck(String fulName,String address,String phone,String username,String pass){
        if (mainAcc.getFullname().equals(fulName) == false || mainAcc.getAddress().equals(address) == false ||mainAcc.getPhone().equals(phone) == false ||mainAcc.getUsername().equals(username) == false ||mainAcc.getPassword().equals(pass) == false ){
            return false;
        }
        return true;
    }
}
