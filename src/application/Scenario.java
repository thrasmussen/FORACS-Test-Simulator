package application;

import java.util.ArrayList;

import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.sf.marineapi.nmea.util.Position;


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
		SUT.setPosition(new Position(0, 0, 0));
	}
	
	public void startScenario(){
		System.out.println("Starting Ship");
		getSUT().setShipLastUpdated();
		SUT.setRunning(true);
		new Thread(SUT).start();
	}
	public void stopScenario(){
		System.out.println("Stopping Ship");
		SUT.setRunning(false);
	}
}


