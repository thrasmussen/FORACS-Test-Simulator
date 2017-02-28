package entities;

import java.util.ArrayList;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import net.sf.marineapi.nmea.util.Position;

public class Ship extends Task<Integer> {
	
	private String name;
	private String type;
	private String hullnumber;
	private double length;
	private double width;
	private DoubleProperty headingRad = new SimpleDoubleProperty();
	private DoubleProperty pitch = new SimpleDoubleProperty();
	private DoubleProperty roll = new SimpleDoubleProperty();
	private DoubleProperty rudder = new SimpleDoubleProperty();
	private DoubleProperty speed = new SimpleDoubleProperty();
	private ObservableList<Sensor> sensors = FXCollections.observableList(new ArrayList<Sensor>());
	private ObservableList<Interface> interfaces = FXCollections.observableList(new ArrayList<Interface>());
	private Position position;
	private boolean isRunning;
	private double shipLastUpdate = 0;
	
	public Ship() {
		// TODO Auto-generated constructor stub
	}

	public Ship(String name, String type, String hullnumber, double lenght, double width){
		this.name = name;
		this.type = type;
		this.hullnumber = hullnumber;
		this.length = lenght;
		this.width = width;	
	}
	
	public boolean getIsRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHullnumber() {
		return hullnumber;
	}

	public void setHullnumber(String hullnumber) {
		this.hullnumber = hullnumber;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public DoubleProperty propertyHeadingRad() {
		return headingRad;
	}
	
	public double getHeadingRad(){
		return headingRad.get();
	}
	public double getHeadingRad360(){
		if (headingRad.get()<0)return headingRad.get()+(2*Math.PI);
		return headingRad.get();
	}
	public double getHeadingDeg(){
		return Math.toDegrees(headingRad.get());
	}
	public double getHeadingDeg360(){
		return Math.toDegrees(getHeadingRad360());
	}

	public void setHeadingRad(double headingRad) {
		this.headingRad.set(Math.atan2(Math.sin(headingRad), Math.cos(headingRad)));
	}
	public void setHeadingDeg(double headingDeg){
		this.headingRad.set(Math.toDegrees(headingDeg));
	}

	public DoubleProperty propertyPitch() {
		return pitch;
	}
	
	public double getPitch() {
		return pitch.get();
	}

	public void setPitch(double pitch) {
		this.pitch.set(pitch);
	}

	public DoubleProperty propertyRoll(){
		return roll;
	}
	
	public double getRoll() {
		return roll.get();
	}

	public void setRoll(double roll) {
		this.roll.set(roll);
	}
	
	public DoubleProperty propertyRudder(){
		return rudder;
	}

	public double getRudder() {
		return rudder.get();
	}

	public void setRudder(double rudder) {
		this.rudder.set(rudder);
	}

	public DoubleProperty propertySpeed(){
		return speed;
	}
	
	public double getSpeed() {
		return speed.get();
	}

	public void setSpeed(double speed) {
		this.speed.set(speed);
	}

	public ObservableList<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(ObservableList<Sensor> sensors) {
		this.sensors = sensors;
	}
	
	public ObservableList<Interface> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(ObservableList<Interface> interfaces) {
		this.interfaces = interfaces;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position positon) {
		this.position = positon;
	}

	@Override
	protected Integer call() throws Exception {
		// TODO Auto-generated method stub
		
		while(isRunning){
			double time = System.nanoTime();
		    double delta_time =  ((time - shipLastUpdate) / 1000000000);
		    shipLastUpdate = time;
		    updateHeading();
		    updatePosition(delta_time);
		}

		return null;
	}
	
	public String toString(){
		return name;
	}
	public void printStatus(){
		String s = "";
		s += "***Status***\n";
		s += "name: " + name + "\n";
		s += "type: " + type + "\n";
		s += "hullnumber: " + hullnumber + "\n";
		s += "lenth: " + length + "\n";
		s += "width" + width + "\n";
		s += "heading: " + headingRad.get() + "\n";
		s += "pitch: " + pitch.get() + "\n";
		s += "roll: " + roll.get() + "\n";
		s += "rudder: " + rudder.get() + "\n";
		s += "speed: " + speed.get() + "\n";
		s += "sensors: " + sensors.toString() + "\n";
		s += "postition: " + position.toString() + "\n";
		System.out.println(s);	
	}
	public double updateHeading(){
		headingRad.set(headingRad.get() + rudder.get() * 0.0001); 
		return headingRad.get();
	}
	public Position updatePosition(double deltaTime){
		position = calculations.GeoCalculations.geoPosFromDistance(position, deltaTime, headingRad.get());
		return position;
	}


}
