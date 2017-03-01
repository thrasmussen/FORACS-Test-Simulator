package application;

import entities.Interface;
import entities.Sensor;
import entities.Target;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SensorController {
	private Sensor sensor;
	private Scenario scenario;

    @FXML private TextField typeTxt;
    @FXML private TextField parallaxZTxt;
    @FXML private TextField parallaxXTxt;
    @FXML private TextField parallaxYTxt;
    @FXML private Slider missmarkSlider;
    @FXML private Slider noiceSlider;
    @FXML private TextField nameTxt;
    @FXML private ComboBox<Target> targetCbx;
    @FXML private Button sensorCloseBtn;
    @FXML private TextField sensorIDTxt;
    @FXML private ComboBox<Interface> outputCmbBox;

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
    	sensor.setTarget(targetCbx.getValue());
    	
    	Interface output = outputCmbBox.getValue();
    	if (output != sensor.getOutput() && sensor.getOutput()!=null)sensor.getOutput().getSensors().remove(sensor);
    	if (output!= null){
    		if(!output.getSensors().contains(sensor))output.getSensors().add(sensor);
    		
    		sensor.setOutput(output);
    	}
    	
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
		outputCmbBox.setValue(sensor.getOutput());
		targetCbx.setValue(sensor.getTarget());
		
	}
	public void setScenario(Scenario scenario){
		this.scenario = scenario;
		
		targetCbx.setItems(scenario.getStaticTargets());
		outputCmbBox.setItems(scenario.getSUT().getInterfaces());
	}




}
