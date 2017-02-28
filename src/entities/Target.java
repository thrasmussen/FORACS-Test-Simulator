package entities;

import net.sf.marineapi.nmea.util.Position;

public class Target{


	private String name; 
	private String type;
	private String description;
	private Position position = new Position(0,0);
	
	public Target(){
		
	}
	public Target(String name, String type, String description){
		this.name = name;
		this.type = type;
		this.description = description;
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
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public String toString(){
		return name;
	}
	public void printStatus(){
		String s = "";
		s += "***Status***\n";
		s += "name: " + name + "\n";
		s += "type: " + type + "\n";
		s += "description: " + description + "\n";
		s += "position: " + position + "\n";
		System.out.println(s);
	}
	
	
}
