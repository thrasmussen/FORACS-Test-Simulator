package application;

import java.util.ArrayList;

import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Scenario {
	
	private Ship SUT = new Ship();
	private ObservableList<Target> staticTargets = FXCollections.observableList(new ArrayList<Target>());
	

	
	public Ship getSUT() {
		return SUT;
	}
	public void setSUT(Ship sUT) {
		SUT = sUT;
	}
	public ObservableList<Target> getStaticTargets() {
		return staticTargets;
	}
	public void setStaticTargets(ObservableList<Target> staticTargets) {
		this.staticTargets = staticTargets;
	}

	public Scenario(){
		SUT = new Ship("HMS Neversail", "Frigate", "F123", 123, 23);
	}
	
	public void startScenario(){
		
	}
	public void stopScenario(){
		
	}
}


