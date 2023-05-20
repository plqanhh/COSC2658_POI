package geniemoviesandgames.controller;

import java.io.IOException;

import geniemoviesandgames.Switchingscene;
import geniemoviesandgames.backend.mainSystem;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class LoginController extends Switchingscene {

    @FXML
    TextField usernameInputTextField;
    @FXML
    Text warningText;
    @FXML
    PasswordField passwordInput;

    @FXML
    private void loginProcedure() throws IOException {

        if (usernameInputTextField.getText().isEmpty() || passwordInput.getText().isEmpty()) {
            warningText.setText("Please enter username or password");
        }else if(usernameInputTextField.getText().equals("123") && passwordInput.getText().equals("123")){
            switchToAdmin();
        } else if(mainSystem.acountLogin(usernameInputTextField.getText(), passwordInput.getText())){
            switchToMenu();
        } else {
            warningText.setText("You enter the wrong username or password");
        }

        // loop through all accounts and check username/pwd
    }

    

}
