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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author david_000
 */
public class LoginController implements Initializable
{

	@FXML
	TextField username;
	@FXML
	PasswordField password;

	String userName;
	String passWord;

	@FXML
	Label errorMessage;

	@FXML
	public void handleEnterPressed(KeyEvent event)
	{
		System.out.println("TESTING");
		if (event.getCode() == KeyCode.ENTER)
		{
			signIn();
		}
	}

	public void signIn()
	{
		// this is where I would check the DB for a matching user and password.
		// I would want to enable hashing of the password too.
		userName = username.getText();
		passWord = password.getText();

		if (userName.length() <= 0)
		{
			ShadowRunApp.setErrorMessage("No Username entered", errorMessage);
			return;
		} else if (passWord.length() <= 0)
		{
			ShadowRunApp.setErrorMessage("No Password entered", errorMessage);
			return;
		} else
		{
			errorMessage.setText("");
		}

		// Need to hash password here to check it
		String hashedPswdToCheck = DataBaseConnector.hashPassword(passWord);

		boolean success = DataBaseConnector.login(userName, errorMessage, hashedPswdToCheck);
		if (success)
		{
			ShadowRunApp.setUser(userName); // Sets the user of who is logged in.

			try
			{
				ShadowRunApp.createLoadScene();

			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public void newUser()
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.createNewUserScene();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void newPassword()
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.createNewPassword();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		// TODO
	}

}
