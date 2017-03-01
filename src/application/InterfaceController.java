package application;

import entities.Interface;
import entities.Sensor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InterfaceController {
	
	private Interface output;

    @FXML
    private ChoiceBox<String> typeChoBox;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField datarateTxt;

    @FXML
    private Button sensorCloseBtn;
    @FXML
    private ListView<Sensor> sensorsList;
    
    
    public void initialize(){
    	ObservableList<String> options = FXCollections.observableArrayList();
			options.add(Interface.IS_EMM);
			options.add(Interface.IS_NMEA);
			options.add(Interface.IS_SIIS);
			typeChoBox.setItems(options);
			

    }

    @FXML
    void CloseBtnAction(ActionEvent event) {
    	output.setName(nameTxt.getText());
    	output.setType(typeChoBox.getValue());
    	output.setDataRate(Integer.parseInt(datarateTxt.getText()));
    	
		Stage stage = (Stage) nameTxt.getScene().getWindow();
	    stage.close();
    }

	public Interface getOutput() {
		return output;
	}

	public void setOutput(Interface output) {
		this.output = output;
		nameTxt.setText(output.getName());
		datarateTxt.setText("" + output.getDataRate());
		typeChoBox.setValue(output.getType());
		sensorsList.setItems(output.getSensors());
	}

}
