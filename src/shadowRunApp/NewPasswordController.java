package shadowRunApp;

import java.net.URL;
import java.util.ResourceBundle;

import character.DataBaseConnector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class NewPasswordController implements Initializable {

	@FXML
	TextField username;
	@FXML
	PasswordField password;
	@FXML
	PasswordField conPassword;
	@FXML
	Label errorMessage;
	
	public void newPassword() {
		String userName = username.getText();
		String pswd = password.getText();
		String conPswd = conPassword.getText();
		
		if (userName.length() <= 0) {
			ShadowRunApp.setErrorMessage("No Username entered", errorMessage);
			return;
		} else if (pswd.length() <= 0) {
			ShadowRunApp.setErrorMessage("No Password entered", errorMessage);
			return;
		} else {
			errorMessage.setText("");
		}

		if (pswd.equals(conPswd)) {
			String hashedNewPswd = DataBaseConnector.hashPassword(conPswd);
			DataBaseConnector.setNewPassword(errorMessage, userName, hashedNewPswd);
		}
		else
		{
			ShadowRunApp.setErrorMessage("Passwords do not match", errorMessage);
			return;
		}
		
		try {
			ShadowRunApp.createLogInScene();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
