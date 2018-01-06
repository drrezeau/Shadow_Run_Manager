/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadowRunApp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import character.DataBaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import pdfWriter.MyPdfWriter;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import shadowRunApp.ShadowRunApp;

/**
 *
 * @author david_000
 */
public class MainMenuController implements Initializable
{

	@FXML
	private TextArea characterDisplay;
	@FXML
	private Button spells;
	@FXML
	private Button other;
	@FXML
	private MenuItem saveDB;

	/*************************************************
	 * The functions which control what happens when the users click on different buttons. One for each button
	 **********************************************/
	@FXML
	private void loadMenu()
	{

		try
		{
			new ShadowRunApp();
			ShadowRunApp.createLoadScene();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void attributes(ActionEvent event)
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.createAttributeScene();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void ids(ActionEvent event)
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.createIDAndCurrency();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void skills(ActionEvent event)
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.createSkillScene();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void qualities(ActionEvent event)
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.createQualityScene();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void contacts(ActionEvent event)
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.createContactScene();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void armor(ActionEvent event)
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.createArmorScene();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void meleeWeapons(ActionEvent event)
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.createMeleeWeaponsScene();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void rangedWeapons(ActionEvent event)
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.createRangedWeaponsScene();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void cyberDeck()
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.createCyberDeckScene();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void vehicle()
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.createVehicleScene();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void gear()
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.createGearScene();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void adept()
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.createAdeptScene();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void augmentations()
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.createAugmentationScene();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void spells()
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.createSpellScene();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void savePdf()
	{
		new MyPdfWriter().writeToPdf(ShadowRunApp.character);
	}
	
	@FXML
	private void saveToDB()
	{
		Alert alert = new Alert(AlertType.INFORMATION, "Saving. . .");
		alert.setContentText("Saving to Database. . .");
		alert.show();
		boolean saved = DataBaseConnector.saveToDB(new ShadowRunApp().getCharacter());
		if (saved)
		{
			alert.close();
			alert = new Alert(AlertType.INFORMATION, "Save Successful.");
			alert.setTitle("Saved Message");
			alert.setHeaderText("Saved");
			alert.showAndWait();
		} else
		{
			alert.close();
			alert = new Alert(AlertType.INFORMATION, "Save Unsuccessful.");
			alert.setTitle("Saved Message");
			alert.setHeaderText("Not Saved");
			alert.setContentText("The character could not be saved. Check internet connection and try again");
			alert.showAndWait();
		}
	}

	@FXML
	private void saveLocally()
	{
		FileChooser fc = new FileChooser();
		
		fc.setTitle("Save Character to File");
		fc.getExtensionFilters().add(new ExtensionFilter("Character Files", "*.ser"));
		fc.setInitialDirectory(new File("Shadow Run/myCharacters"));
		fc.setInitialFileName(ShadowRunApp.character.getName() + ".ser");
		File file = fc.showSaveDialog(ShadowRunApp.primaryStage);
		String filePath = file.getPath();
		
		if (!filePath.contains(".ser"))
			filePath.concat(".ser");
		
		try
		{
			FileOutputStream fos = new FileOutputStream(filePath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(ShadowRunApp.character);
			oos.close();
			fos.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	private void changeKarma()
	{
		TextInputDialog dialog = new TextInputDialog(Integer.toString(new ShadowRunApp().getCharacter().getKarma()));
		dialog.setTitle("");
		dialog.setHeaderText("Karma");
		dialog.setContentText("Please enter the Karma amount:");
		String input;
		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		input = result.get();

		new ShadowRunApp().getCharacter().setKarma(Integer.parseInt(input));
		this.initialize(null, null);

	}

	/*******************************************************/

	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		// TODO
		if (!DataBaseConnector.connectionStatus)
		{
			saveDB.setDisable(true);
		}
		else
		{
			saveDB.setDisable(false);
		}
		// Things not yet implemented for users

		character.Character me = new ShadowRunApp().getCharacter();
		// Checks if the user has magic, if they don't then doesn't let them use this button
		if (me.isHasMagic())
		{
			spells.setDisable(false);
			other.setDisable(false);
		}

		String text2Display = "";
		text2Display += "Player's Name: " + me.getPlayer();
		text2Display += "\nCharacter's Name: " + me.getName();
		text2Display += "\nCharacter's Alias: " + me.getAlias();
		text2Display += "\nCharacter's Age: " + me.getAge();
		text2Display += "\nCharacter's Height: " + me.getHeightFeet() + "ft " + me.getHeightInch() + "in";
		text2Display += "\nCharacter's Weight: " + me.getWeight() + "(pounds)";
		text2Display += "\nCharacter's Sex: " + me.getGender();
		text2Display += "\nCharacter's Metatype: " + me.getMetatype();
		text2Display += "\nKarma: " + me.getKarma();
		text2Display += "\nLifestyle: " + me.getLifestyle();
		text2Display += "\nNuyen: " + me.getNuyen() + "\n";

		text2Display += "\nBody: " + me.getBody() + "\n";
		text2Display += "Agility: " + me.getAgility() + "\n";
		text2Display += "Reaction: " + me.getReaction() + "\n";
		text2Display += "Strength: " + me.getStrength() + "\n";
		text2Display += "Willpower: " + me.getWillpower() + "\n";
		text2Display += "Logic: " + me.getLogic() + "\n";
		text2Display += "Intuition: " + me.getIntuition() + "\n";
		text2Display += "Charisma: " + me.getCharisma() + "\n";
		text2Display += "Edge: " + me.getEdge() + "\n";
		text2Display += "Magic: " + me.getMagic() + "\n";

		text2Display += "\n--- LICENSES ---\n";
		text2Display += me.creataLicenseString();
		text2Display += "\n--- SKILLS ---\n";
		text2Display += me.createSkillString();
		text2Display += "\n--- QUALITIES ---\n";
		text2Display += me.createQualityString();
		text2Display += "\n--- CONTACTS ---\n";
		text2Display += me.createContactString();
		text2Display += "\n--- MELEE WEAPONS ---\n";
		text2Display += me.createMeleeWeaponsString();
		text2Display += "\n--- RANGED WEAPONS ---\n";
		text2Display += me.createRangedWeaponsString();
		text2Display += "\n--- ARMOR ---\n";
		text2Display += me.createArmorString();
		text2Display += "\n--- GEAR ---\n";
		text2Display += me.createGearString();
		text2Display += "\n--- CYBERDECK ---\n";
		text2Display += me.getCyberdeck().displayString();
		text2Display += "\n--- VEHICLE ---\n";
		// text2Display += me.getVehicle().toDisplay();
		text2Display += "\n--- AUGMENTATIONS ---\n";
		text2Display += me.createAugmentationsString();
		if (me.isHasMagic())
		{
			text2Display += "\n--- ADEPT POWERS ---\n";
			text2Display += me.createAdeptPowersString();
			text2Display += "\n--- SPELLS ---\n";
			text2Display += me.createSpellsString();
		}

		characterDisplay.setText(text2Display);
	}

}
