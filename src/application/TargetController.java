package application;

import entities.Target;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.sf.marineapi.nmea.util.Position;

public class TargetController {
	
	private Target target;

    @FXML
    private TextField typeTxt;

    @FXML
    private TextField latitudeTxt;

    @FXML
    private TextField altitudeTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField longitudeTxt;

    @FXML
    private Button CloseBtn;

    @FXML
    private TextField descriptionTxt;

    @FXML
    void CloseBtnAction(ActionEvent event) {
    	System.out.println("Close");
    	target.setName(nameTxt.getText());
    	target.setType(typeTxt.getText());
    	target.setDescription(descriptionTxt.getText());
    	target.setPosition(new Position(Double.parseDouble(latitudeTxt.getText()), Double.parseDouble(longitudeTxt.getText()), Double.parseDouble(altitudeTxt.getText())));
    	target.printStatus();;
    	
    	
		Stage stage = (Stage) nameTxt.getScene().getWindow();
	    stage.close();
    }

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
		nameTxt.setText(target.getName());
		typeTxt.setText(target.getType());
		descriptionTxt.setText(target.getDescription());
		latitudeTxt.setText(""+target.getPosition().getLatitude());
		longitudeTxt.setText(""+target.getPosition().getLongitude());
		altitudeTxt.setText(""+target.getPosition().getAltitude());
	}

}
