package geniemoviesandgames.controller;

import java.io.IOException;

import geniemoviesandgames.Switchingscene;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class registerController extends Switchingscene {

    @FXML Text warningText;
    @FXML TextField userNameTextField;
    @FXML PasswordField firstPasswordfield;
    @FXML PasswordField rePasswordfield;
    static String username;
    static String password;
     
     public static String getUsername(){
        return username;
    }
    public static String getPassword(){
        return password;
    } 
    @FXML
    private void registerProcedure() throws IOException{
        
        if(userNameTextField.getText().isEmpty() || firstPasswordfield.getText().isEmpty() || rePasswordfield.getText().isEmpty()){
            warningText.setText("Please enter the missing infomation!");
        }else if(firstPasswordfield.getText().equals(rePasswordfield.getText())){
            username = userNameTextField.getText();
            password = firstPasswordfield.getText();
            switchToNewUser();
        }else{
            warningText.setText("The two passwords are not matching!");
        }
    }

}