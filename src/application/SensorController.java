package application;

import entities.Sensor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SensorController {
	private Sensor sensor;

    @FXML private TextField typeTxt;
    @FXML private TextField parallaxZTxt;
    @FXML private TextField parallaxXTxt;
    @FXML private TextField parallaxYTxt;
    @FXML private Slider missmarkSlider;
    @FXML private Slider noiceSlider;
    @FXML private TextField nameTxt;
    @FXML private ComboBox<?> targetCbx;
    @FXML private Button sensorCloseBtn;
    @FXML private TextField sensorIDTxt;

    public void initialize()  {
    		System.out.println(this.getSensor());

    }
    
    @FXML
    void nameTxtAction(ActionEvent event) {
    	sensor.setName(nameTxt.getText());
    	System.out.println(sensor.getName());
    }

    @FXML
    void typeTxtAction(ActionEvent event) {
    	System.out.println(sensor);
    	sensor.setType(typeTxt.getText());
    }

    @FXML
    void parallaxXTxtAction(ActionEvent event) {
    	sensor.setxParallax(Double.parseDouble(parallaxXTxt.getText()));
    }

    @FXML
    void parallaxYTxtAction(ActionEvent event) {
    	sensor.setyParallax(Double.parseDouble(parallaxYTxt.getText()));
    }

    @FXML
    void parallaxZTxtAction(ActionEvent event) {
    	sensor.setzParallax(Double.parseDouble(parallaxZTxt.getText()));
    }

    @FXML
    void sensorIDTxtAction(ActionEvent event) {

    }

    @FXML
    void targetCbxAction(ActionEvent event) {

    }

    @FXML
    void sensorCloseBtnAction(ActionEvent event) {
    	System.out.println("Close");
    	sensor.setName(nameTxt.getText());
    	sensor.setType(typeTxt.getText());
    	sensor.setSensorID(sensorIDTxt.getText());
    	sensor.setxParallax(Double.parseDouble(parallaxXTxt.getText()));
    	sensor.setyParallax(Double.parseDouble(parallaxYTxt.getText()));
    	sensor.setzParallax(Double.parseDouble(parallaxZTxt.getText()));
    	
    	
    	sensor.printStatus();
    	
    	
    	
		Stage stage = (Stage) nameTxt.getScene().getWindow();
	    stage.close();

    }

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
		nameTxt.setText(sensor.getName());
		typeTxt.setText(sensor.getType());
		sensorIDTxt.setText(sensor.getSensorID());
		parallaxXTxt.setText(""+sensor.getxParallax());
		parallaxYTxt.setText(""+sensor.getyParallax());
		parallaxZTxt.setText(""+sensor.getzParallax());
		
	}


	public void setName(String str){
		nameTxt.setText(str);
	}

}
