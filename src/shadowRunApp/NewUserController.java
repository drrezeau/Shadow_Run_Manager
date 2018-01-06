/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadowRunApp;

import java.net.URL;
import java.util.ResourceBundle;

import character.DataBaseConnector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author david_000
 */
public class NewUserController implements Initializable {

    
    @FXML TextField username;
    @FXML TextField password;
    @FXML TextField conPassword;
    
    @FXML Label errorMessage;
    
    public void createUser() {
        String userName = username.getText();
        String passWord = password.getText();
        String confirmPass = conPassword.getText();
        
        if (userName.length() <=0) {
            errorMessage.setText("Enter a username");
            return;
        }
        else if (passWord.length() <= 6) {
            errorMessage.setText("Password not long enough");
            return;
        }
        else if (!passWord.equals(confirmPass)) {
            errorMessage.setText("Passwords do not Match");
            return;
        } else {
            errorMessage.setText("");
        }
        
         if (!DataBaseConnector.createNewUser(userName, passWord, errorMessage))
         	return;
         
         try {
            new ShadowRunApp();
				ShadowRunApp.createLogInScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
