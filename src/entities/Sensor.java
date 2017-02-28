package entities;

import calculations.GeoCalculations;
import net.sf.marineapi.nmea.util.Position;

public class Sensor {
	
	private String name;
	private String type; 
	private String description;
	private String sensorID;
	private Ship ownShip;
	private Target target;
	private double xParallax;
	private double yParallax;
	private double zParallax;
	private Interface output;
	

	
	public Sensor(){
		xParallax = 0;
		yParallax = 0;
		zParallax = 0;
	}
	public Sensor(String name, String type, String description){
		this.name = name;
		this.type = type;
		this.description = description;
		xParallax = 0;
		yParallax = 0;
		zParallax = 0;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Ship getOwnShip() {
		return ownShip;
	}
	public void setOwnShip(Ship ownShip) {
		this.ownShip = ownShip;
	}
	public double getxParallax() {
		return xParallax;
	}
	public void setxParallax(double xParallax) {
		this.xParallax = xParallax;
	}
	public double getyParallax() {
		return yParallax;
	}
	public void setyParallax(double yParallax) {
		this.yParallax = yParallax;
	}
	public double getzParallax() {
		return zParallax;
	}
	public void setzParallax(double zParallax) {
		this.zParallax = zParallax;
	}
	public String toString(){
		return name;
	}
	public Position getPosition(){
		if (xParallax == 0 && yParallax == 0) {
			Position pos = ownShip.getPosition();
			pos.setAltitude(ownShip.getPosition().getAltitude()+zParallax);
			return pos;
		}
		return GeoCalculations.geoPosFromParallax(this);
	}
	public String getSensorID() {
		return sensorID;
	}
	public void setSensorID(String sensorID) {
		this.sensorID = sensorID;
	}
	public Target getTarget() {
		return target;
	}
	public void setTarget(Target target) {
		this.target = target;
	}
	public Interface getOutput() {
		return output;
	}
	public void setOutput(Interface output) {
		this.output = output;
	}
	public void printStatus(){
		String s = returnStatus();
		System.out.println(s);	
	}
	public String returnStatus(){
		String s = "";
		s += "***Status***\n";
		s += "name: " + name + "\n";
		s += "type: " + type + "\n";
		s += "description: " + description + "\n";
		s += "sensorID: " + sensorID + "\n";
		s += "target: " +  ((target == null) ? "N/A" : target) + "\n";
		s += "xParallax: " + xParallax + "\n";
		s += "yParallax: " + yParallax + "\n";
		s += "zParallax: " + zParallax + "\n";
		return s;
	}

	
}
