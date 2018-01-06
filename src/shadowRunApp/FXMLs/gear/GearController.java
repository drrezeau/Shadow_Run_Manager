/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shadowRunApp.FXMLs.gear;

import character.Gear;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import shadowRunApp.ShadowRunApp;

/**
 * FXML Controller class
 *
 * @author david_000
 */
public class GearController implements Initializable {

    
    @FXML
    private TextArea display;
    
    @FXML
    public void add(ActionEvent event) {
        try {
            new ShadowRunApp();
				ShadowRunApp.addGearScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void edit(ActionEvent event) {
       //Dialog box that allows the user to input something.
       TextInputDialog dialog = new TextInputDialog();
       dialog.setTitle("Gear to Edit");   
       dialog.setHeaderText("Gear");
       dialog.setContentText("Please enter the piece of gear to edit:");
       String name;
       // Traditional way to get the response value.
       Optional<String> result = dialog.showAndWait();
       name = result.get();
       
       name = name.toLowerCase();
       ArrayList<Gear> gear = new ShadowRunApp().getCharacter().getGear();
       for (int i = 0; i < gear.size(); i++) {
           String temp = gear.get(i).getName();
           if (temp.toLowerCase().equals(name)) {

               try {
                    new ShadowRunApp();
						ShadowRunApp.editGearScene(gear.get(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
           }
       }
    }
    public void delete() {
        //Dialog box that allows the user to input something.
       TextInputDialog dialog = new TextInputDialog();
       dialog.setTitle("Gear to Delete");   
       dialog.setHeaderText("Gear");
       dialog.setContentText("Please enter the piece of gear to delete:");
       String name;
       // Traditional way to get the response value.
       Optional<String> result = dialog.showAndWait();
       name = result.get();
       
       new ShadowRunApp().getCharacter().deleteGear(name);
       
       this.initialize(null, null);
    }
    
    @FXML
    public void back(ActionEvent event) {
        try {
            new ShadowRunApp();
				ShadowRunApp.back();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String text2Display = "";
        text2Display += "--- GEAR ---\n";
        text2Display += new ShadowRunApp().getCharacter().createGearString();
        
        display.setText(text2Display);
    }    
    
}
