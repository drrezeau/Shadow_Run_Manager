/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadowRunApp;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import character.DataBaseConnector;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import shadowRunApp.ShadowRunApp;

//import character.*;
/**
 * FXML Controller class
 *
 * @author david_000
 */
public class LoadNewController implements Initializable
{

	@FXML
	TextArea characters;
	@FXML
	ListView<String> list;
	@FXML
	Button signOut;

	public void load(ActionEvent event)
	{
		boolean success = false;
		if (DataBaseConnector.connectionStatus)
		{
			success = ShadowRunApp.loadCharacter();
		} else
		{
			success = ShadowRunApp.loadCharacterLocally();
		}

		if (success)
		{
			try
			{
				ShadowRunApp.createMainMenu();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}

	public void newCharacter(ActionEvent event)
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.createNewCharacter();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void deleteCharacter()
	{
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Character to Delete");
		// dialog.setHeaderText(displayNames);
		dialog.setContentText("Please enter the character you want to delete from the database:\n");
		String name;
		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		name = result.get();

		if (DataBaseConnector.deleteFromDB(name))
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Character successfully deleted from database");
			alert.setHeaderText("Delete Successful");
			alert.setTitle("Deletion Successful");
			alert.showAndWait();
		}

		characters.clear();
		Connection conn = DataBaseConnector.makeConn();
		ArrayList<String> myList = new ArrayList<String>();
		try
		{
			Statement stmt = conn.createStatement();
			String sql;
			sql = "select characterName FROM characters WHERE user='" + new ShadowRunApp().getUser() + "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())
			{
				myList.add(rs.getString("characterName"));
			}
		} catch (Exception e)
		{

		}
		for (String character : myList)
		{
			characters.appendText(character + '\n');
		}
	}

	public void signOut()
	{
		try
		{
			ShadowRunApp.createLogInScene();
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
		if (!DataBaseConnector.connectionStatus)
		{
			signOut.setDisable(true);
		}
		Connection conn = DataBaseConnector.makeConn();
		ArrayList<String> myList = new ArrayList<String>();
		try
		{
			Statement stmt = conn.createStatement();
			String sql;
			sql = "select characterName FROM characters WHERE user='" + new ShadowRunApp().getUser() + "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())
			{
				myList.add(rs.getString("characterName"));
			}
		} catch (Exception e)
		{

		}
		list.setItems(FXCollections.observableArrayList(myList));
	}

}
