/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadowRunApp;

import shadowRunApp.FXMLs.contacts.EditContactController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import pdfWriter.MyPdfWriter;
import character.*;
import character.Character;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
//import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import shadowRunApp.FXMLs.adeptPowers.EditAdeptPowerController;
import shadowRunApp.FXMLs.armor.EditArmorController;
import shadowRunApp.FXMLs.augmentations.EditAugmentationController;
import shadowRunApp.FXMLs.gear.EditGearController;
import shadowRunApp.FXMLs.meleeWeapons.EditMeleeWeaponController;
import shadowRunApp.FXMLs.qualities.EditQualityController;
import shadowRunApp.FXMLs.rangedWeapons.EditRangedWeaponController;
import shadowRunApp.FXMLs.skills.EditSkillController;
import shadowRunApp.FXMLs.spells.EditSpellController;

/**
 *
 * @author david_000
 */
public class ShadowRunApp extends Application
{

	static character.Character character = new character.Character();
	static String associatedUser = null;

	public static Stage primaryStage;
	
	public static MenuBar menu;

	public static Scene scene;
	@Override
	public void start(Stage stage) throws Exception
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Loading Shadowrun Character Manager. . .");
		alert.setTitle("Loading...");
		alert.setContentText("Please wait while Loading. . .");
		alert.show();
		
		primaryStage = stage;
		DataBaseConnector.makeConn();
		primaryStage.setResizable(false);
		scene = new Scene(new Group());
		
		if (DataBaseConnector.connectionStatus)
		{
			createLogInScene();
		}
		else
		{
			createLoadScene();
		}

		primaryStage.show();
		centerStage();

		alert.hide();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		launch(args);
	}

	public static void setErrorMessage(String text, Label errorMessage)
	{
		errorMessage.setText(text);
		errorMessage.prefWidth(errorMessage.getText().length());
		errorMessage.setAlignment(Pos.CENTER);
	}

	/*******************************************************
	 ******************* ButtonControls *********************
	 ********************************************************/

	/**
	 * @throws java.lang.Exception
	 */
	public static void createLogInScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/login.fxml"));
		Parent main = (Parent) loader.load();

		scene.setRoot(main);
		scene.getStylesheets().add("Styles/myStyles.css");

		primaryStage.setScene(scene);

		centerStage();
	}

	public static void createNewPassword() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/newPassword.fxml"));
		Parent main = (Parent) loader.load();

		scene.setRoot(main);

		scene.getStylesheets().add("Styles/myStyles.css");

		primaryStage.setScene(scene);

		centerStage();
	}

	public static void createNewUserScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/newUser.fxml"));
		Parent main = (Parent) loader.load();

		scene.setRoot(main);

		scene.getStylesheets().add("Styles/myStyles.css");

		primaryStage.setScene(scene);

		centerStage();
	}

	public static void createLoadScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/loadNew.fxml"));
		Parent main = (Parent) loader.load();

		scene.setRoot(main);

		scene.getStylesheets().add("Styles/myStyles.css");

		primaryStage.setScene(scene);

		centerStage();
//		scene.setCursor(Cursor.DEFAULT);
	}

	public static void createNewCharacter() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/newCharacter.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();
	}

	public static void createMainMenu() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/mainMenu.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
//		((AnchorPane) scene.getRoot()).getChildren().add(menu);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		scene.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth,
					Number newSceneWidth)
			{
				System.out.println("Width: " + newSceneWidth);
				if (newSceneWidth.intValue() <= 700)
				{
					// scene.getStylesheets().add("Styles/700myStyles.css");
				}
			}
		});

		scene.heightProperty().addListener(new ChangeListener<Number>()
		{
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight,
					Number newSceneHeight)
			{
				System.out.println("Height: " + newSceneHeight);
			}
		});

		centerStage();
	}

	public static void createIDAndCurrency() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/IDs&currency.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();
	}

	public static void createAttributeScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/attributes/attributes.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();
	}

	public static void createSkillScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/skills/skills.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();
	}

	public static void createQualityScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/qualities/qualities.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();
	}

	public static void createContactScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/contacts/contacts.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();
	}

	public static void createArmorScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/armor/armor.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();
	}

	public static void createMeleeWeaponsScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/meleeWeapons/meleeWeapons.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();
	}

	public static void createRangedWeaponsScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/rangedWeapons/rangedWeapons.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();
	}

	public static void createCyberDeckScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/cyberdeck/cyberdeck.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();
	}

	public static void createVehicleScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/vehicle/vehicle.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();
	}

	public static void createGearScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/gear/gear.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();
	}

	public static void createSpellScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/spells/spells.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();
	}

	public static void createAugmentationScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/augmentations/augmentations.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();
	}

	public static void createAdeptScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/adeptPowers/adeptPower.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();
	}

	public static void back() throws Exception
	{
		ShadowRunApp.createMainMenu();
	}

	/*******************************************************
	 ******************** Scenes for Adding *****************
	 ********************************************************/
	public static void addLicenseScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/licenses/addLicense.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void addSkillScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/skills/addSkill.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();
	}

	public static void addQualityScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/qualities/addQuality.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void addContactScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/contacts/addContact.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void addArmorScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/armor/addArmor.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void addMeleeWeaponScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/meleeWeapons/addMeleeWeapon.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void addRangedWeaponScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/rangedWeapons/addRangedWeapon.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void addGearScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/gear/addGear.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void addSpellScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/spells/addSpell.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void addAugmentationScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/augmentations/addAugmentation.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void addAdeptScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/adeptPowers/addAdeptPower.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void addVehicleScene() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/vehicle/addVehicle.fxml"));
		Parent main = (Parent) loader.load();

		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();
	}
	/*******************************************************
	 ****************** Edit Scenes *************************
	 ********************************************************/
	public static void editContactScene(Contact contact) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/contacts/editContact.fxml"));
		Parent main = (Parent) loader.load();

		addTextEditContacts(loader, contact);
		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void editSkillScene(Skill skill) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/skills/editSkill.fxml"));
		Parent main = (Parent) loader.load();

		addTextEditSkill(loader, skill);
		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window

	}

	public static void editQualityScene(Quality quality) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/qualities/editQuality.fxml"));
		Parent main = (Parent) loader.load();

		addTextEditQuality(loader, quality);
		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void editArmorScene(Armor armor) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/armor/editArmor.fxml"));
		Parent main = (Parent) loader.load();

		addTextEditArmor(loader, armor);
		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void editMeleeWeaponScene(MeleeWeapon mw) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/meleeWeapons/editMeleeWeapon.fxml"));
		Parent main = (Parent) loader.load();

		addTextEditMeleeWeapon(loader, mw);
		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void editRangedWeaponScene(RangedWeapon rw) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/rangedWeapons/editRangedWeapon.fxml"));
		Parent main = (Parent) loader.load();

		addTextEditRangedWeapon(loader, rw);
		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void editGearScene(Gear gear) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/gear/editGear.fxml"));
		Parent main = (Parent) loader.load();

		addTextEditGear(loader, gear);
		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void editSpellScene(Spell spell) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/spells/editSpell.fxml"));
		Parent main = (Parent) loader.load();

		addTextEditSpell(loader, spell);
		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void editAugmentationScene(Augmentation aug) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/augmentations/editAugmentation.fxml"));
		Parent main = (Parent) loader.load();

		addTextEditAugmentation(loader, aug);
		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	public static void editAdeptScene(AdeptPower other) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/adeptPowers/editAdeptPower.fxml"));
		Parent main = (Parent) loader.load();

		addTextEditAdeptPower(loader, other);
		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}
	
	public static void editVehiclesScene(Vehicle other) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ShadowRunApp.class.getResource("FXMLs/adeptPowers/editVehicle.fxml"));
		Parent main = (Parent) loader.load();

//		addTextEditAdeptPower(loader, other);
		Scene scene = new Scene(main);
		scene.getStylesheets().add("Styles/myStyles.css");
		primaryStage.setScene(scene);

		centerStage();// centers the window
	}

	/*******************************************************
	 ****************** Extra Functions ********************
	 *******************************************************/

	public static boolean loadCharacter()
	{
		character = new character.Character();
		boolean success = false;
		TextInputDialog dialog = new TextInputDialog();

		dialog.setTitle("Loading a Character");
		// dialog.setHeaderText(displayNames);
		dialog.setContentText("Please enter the Character's name:");
		String name;
		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		name = result.get();

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Loading Character. . .");
		alert.setTitle("Loading...");
		alert.setContentText("Please wait while Loading. . .");
		alert.show();
		int status = DataBaseConnector.loadFromDB(name, character);
		switch (status)
		{
		case 1:
			alert.close();
			success = true;
			break;
		case 2:
			alert.close();
			alert.setAlertType(AlertType.ERROR);
			alert.setTitle("Error while Loading");
			alert.setHeaderText("Error Loading Character");
			alert.setContentText("Error. Check internet connection and try again.");
			alert.show();
			break;
		default:
			alert.close();
			alert.setAlertType(AlertType.ERROR);
			alert.setTitle("Error while Loading");
			alert.setHeaderText("Could not find the given character");
			alert.setContentText("Check the spelling and try again.");
			alert.show();
			break;
		}

		character.sortLicenses();
		character.sortSkills();
		character.sortContacts();
		character.sortQualities();
		character.sortArmor();
		character.sortMeleeWeapons();
		character.sortRangedWeapons();
		return success;
	}

	public static boolean loadCharacterLocally()
	{
		FileChooser fc = new FileChooser();
		
		fc.setTitle("Open Character File");
		fc.getExtensionFilters().add(new ExtensionFilter("Character Files", "*.ser"));
		fc.setInitialDirectory(new File("Shadow Run/myCharacters"));
		File file = fc.showOpenDialog(ShadowRunApp.primaryStage);
		String filePath = file.getPath();
		
		try
		{
			FileInputStream fis = new FileInputStream(filePath);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			ShadowRunApp.character = (Character) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
 	public void setCharacter(character.Character me)
	{
		ShadowRunApp.character = new character.Character(me);
	}

	public character.Character getCharacter()
	{
		return character;
	}

	public static void setUser(String username)
	{
		associatedUser = username;
	}

	public String getUser()
	{
		return associatedUser;
	}

	/*******************************************************
	 ************* Centering of the Stage ******************
	 *******************************************************/
	private static void centerStage()
	{
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
		primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
	}

	/*******************************************************
	 ************* Adding Text to Edits *********************
	 ********************************************************/
	public static void addTextEditContacts(FXMLLoader root, Contact contact)
	{
		EditContactController control = root.getController();
		control.setEdit(contact.getName(), contact.getLoyalty(), contact.getConnection(), contact.getFavor());
	}

	public static void addTextEditSkill(FXMLLoader root, Skill skill)
	{
		EditSkillController control = root.getController();
		control.setText(skill);
	}

	public static void addTextEditQuality(FXMLLoader root, Quality quality)
	{
		EditQualityController control = root.getController();
		control.setText(quality);
	}

	public static void addTextEditArmor(FXMLLoader root, Armor armor)
	{
		EditArmorController control = root.getController();
		control.setText(armor);
	}

	public static void addTextEditMeleeWeapon(FXMLLoader root, MeleeWeapon mw)
	{
		EditMeleeWeaponController control = root.getController();
		control.setText(mw);
	}

	public static void addTextEditRangedWeapon(FXMLLoader root, RangedWeapon rw)
	{
		EditRangedWeaponController control = root.getController();
		control.setText(rw);
	}

	public static void addTextEditGear(FXMLLoader root, Gear gear)
	{
		EditGearController control = root.getController();
		control.setText(gear);
	}

	public static void addTextEditAdeptPower(FXMLLoader root, AdeptPower ap)
	{
		EditAdeptPowerController control = root.getController();
		control.setText(ap);
	}

	public static void addTextEditSpell(FXMLLoader root, Spell spell)
	{
		EditSpellController control = root.getController();
		control.setText(spell);
	}

	public static void addTextEditAugmentation(FXMLLoader root, Augmentation aug)
	{
		EditAugmentationController control = root.getController();
		control.setText(aug);
	}

}
