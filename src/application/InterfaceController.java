package application;

import entities.Interface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class InterfaceController {
	
	private Interface output;

    @FXML
    private ChoiceBox<?> typeChoBox;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField datarateTxt;

    @FXML
    private Button sensorCloseBtn;

    @FXML
    void CloseBtnAction(ActionEvent event) {

    }

	public Interface getOutput() {
		return output;
	}

	public void setOutput(Interface output) {
		this.output = output;
	}

}
