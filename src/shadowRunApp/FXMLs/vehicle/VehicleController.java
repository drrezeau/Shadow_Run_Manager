package shadowRunApp.FXMLs.vehicle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import character.Vehicle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import shadowRunApp.ShadowRunApp;

public class VehicleController implements Initializable
{

	@FXML
	TableView<Vehicle> display;

	@FXML
	TableColumn<Vehicle, String> nameColumn;
	@FXML
	TableColumn<Vehicle, Integer> handColumn;
	@FXML
	TableColumn<Vehicle, Integer> accelColumn;
	@FXML
	TableColumn<Vehicle, Integer> speedColumn;
	@FXML
	TableColumn<Vehicle, Integer> pilotColumn;
	@FXML
	TableColumn<Vehicle, Integer> bodyColumn;
	@FXML
	TableColumn<Vehicle, Integer> armorColumn;
	@FXML
	TableColumn<Vehicle, Integer> sensorColumn;
	@FXML
	TableColumn<Vehicle, String> notesColumn;

	@FXML
	public void back(ActionEvent event)
	{
		try
		{
			ShadowRunApp.back();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	public void add(ActionEvent event)
	{
		try
		{
			new ShadowRunApp();
			ShadowRunApp.addVehicleScene();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	public void edit()
	{
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Vehicle to Edit");
		dialog.setHeaderText("Vehicles");
		dialog.setContentText("Please enter the vehicle to edit:");
		String name;
		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		name = result.get();

		name = name.toLowerCase();
		ArrayList<Vehicle> vehicles = new ShadowRunApp().getCharacter().getVehicles();
		for (int i = 0; i < vehicles.size(); i++)
		{
			String temp = vehicles.get(i).getName();
			if (temp.toLowerCase().equals(name))
			{

				try
				{
					new ShadowRunApp();
					ShadowRunApp.editVehiclesScene(vehicles.get(i));
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	//TODO change this for vehicles
	@FXML
	public void delete()
	{
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Vehicle to Delete");
		dialog.setHeaderText("Vehicles");
		dialog.setContentText("Please enter the Vehicle to delete:");
		String name;
		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		name = result.get();

		new ShadowRunApp().getCharacter().deleteVehicle(name);

		this.initialize(null, null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		ArrayList<Vehicle> temp = new ArrayList<Vehicle>();
		System.out.println(display.getColumns().size());
		temp = new ShadowRunApp().getCharacter().getVehicles();

		System.out.println(temp.size());
		display.setItems(FXCollections.observableArrayList(temp));
		int size = display.getItems().size();
		System.out.println(size);
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		handColumn.setCellValueFactory(new PropertyValueFactory<>("handling"));
		accelColumn.setCellValueFactory(new PropertyValueFactory<>("acceleration"));
		speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));
		pilotColumn.setCellValueFactory(new PropertyValueFactory<>("pilot"));
		bodyColumn.setCellValueFactory(new PropertyValueFactory<>("body"));
		armorColumn.setCellValueFactory(new PropertyValueFactory<>("armor"));
		sensorColumn.setCellValueFactory(new PropertyValueFactory<>("sensor"));
//		notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
		
		for (int i = 0; i < size; i++)
		{
			display.getItems().set(i, temp.get(i));
//			nameColumn.setCellFactory(cellData -> cellData.getValue().birthdayProperty());
		}
		
		
		
		
		
		
		
		
		
		
		
	}

}
