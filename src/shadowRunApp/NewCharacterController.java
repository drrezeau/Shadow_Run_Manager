/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadowRunApp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author c
 */
public class NewCharacterController implements Initializable {

    @FXML TextField player;
    @FXML TextField characterName;
    @FXML TextField alias;
    @FXML TextField age;
    @FXML TextField weight;
    @FXML TextField feet;
    @FXML TextField inch;
    @FXML ChoiceBox<String> metatype;
    @FXML CheckBox hasMagic;
    @FXML CheckBox male;
    @FXML CheckBox female;
    
    public void submit(ActionEvent event) {
        String player1, characterName1, alias1 = "", sex1="undefined", metatype1;
        int age1, weight1;
        boolean hasMagic1 = false;
        
        if (player.getText().isEmpty()) {
            showAlert("player");
            return;
        }
        if (characterName.getText().isEmpty()) {
            showAlert("character name");
            return;
        }
        if (alias.getText().isEmpty()) {
            showAlert("alias");
            return;
        }
        if (metatype.getValue() == null) {
            showAlert("metatype");
            return;
        }
        if (age.getText().isEmpty()) {
            showAlert("age");
            return;
        }
        if (feet.getText().isEmpty()) {
            showAlert("feet");
            return;
        }
        if (inch.getText().isEmpty()) {
            showAlert("inch");
            return;
        }
        if (weight.getText().isEmpty()) {
            showAlert("weight");
            return;
        }
        if (hasMagic.isSelected()) {
            hasMagic1 = true;
        }
        if (male.isSelected()) {
            sex1 = "Male";
        } else if (female.isSelected()) {
            sex1 = "Female";
        }
        int feet1 = Integer.parseInt(feet.getText());
        int inches = Integer.parseInt(inch.getText());

        
        player1 = player.getText();
        characterName1 = characterName.getText();
        alias1 = alias.getText();
        metatype1 = metatype.getValue().toString();
        age1 = Integer.parseInt(age.getText());
        weight1 = Integer.parseInt(weight.getText());
        character.Character character = new character.Character(player1, characterName1, alias1, 
                age1, weight1, feet1, inches, sex1, hasMagic1, metatype1, new ShadowRunApp().getUser());
        
        ShadowRunApp main = new ShadowRunApp();
        main.setCharacter(character);
        
        try {
            ShadowRunApp.createMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void showAlert(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("No " + error + " entered.");
        alert.setHeaderText("Error");
        alert.setTitle("Error");
        alert.showAndWait();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        metatype.setItems(FXCollections.observableArrayList("Human", "Elf", "Dwarf", "Orc", "Troll"));
    }    
    
}
