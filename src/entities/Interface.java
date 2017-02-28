package entities;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fazecast.jSerialComm.SerialPort;

import calculations.GeoCalculations;
import javafx.concurrent.Task;
import net.sf.marineapi.nmea.parser.SentenceFactory;
import net.sf.marineapi.nmea.sentence.GGASentence;
import net.sf.marineapi.nmea.sentence.GLLSentence;
import net.sf.marineapi.nmea.sentence.HDTSentence;
import net.sf.marineapi.nmea.sentence.TalkerId;
import net.sf.marineapi.nmea.util.GpsFixQuality;
import net.sf.marineapi.nmea.util.Position;
import net.sf.marineapi.nmea.util.Time;
import net.sf.marineapi.nmea.util.Units;


public class Interface extends Task<Integer> {
	
	public static final String IS_NMEA = "NMEA";
	public static final String IS_EMM = "EMM";
	public static final String IS_SIIS = "SIIS";
	
	private String name;
	private String type;
	private int dataRate;
	private Sensor[] sensors;
	private SerialPort serialPort;
	private DatagramSocket datagramSocket;
	private boolean isNetwork;
	private boolean isRunning;
	private boolean sendHeading;
	private boolean sendOwnPosition;
	private boolean sendTargetPosition;
	private boolean sendTargetRange;
	private boolean sendTargetBearing;
	private boolean sendOwnHeading;
	private Time t = new Time(); 
	
	public Interface(){
		
	}
	public Interface(String name){
		this.name = name;
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
	public int getDataRate() {
		return dataRate;
	}
	public void setDataRate(int dataRate) {
		this.dataRate = dataRate;
	}
	public Sensor[] getSensors() {
		return sensors;
	}
	public void setSensors(Sensor[] sensors) {
		this.sensors = sensors;
	}
	public SerialPort getSerialPort() {
		return serialPort;
	}
	public void setSerialPort(SerialPort serialPort) {
		this.serialPort = serialPort;
	}
	public DatagramSocket getDatagramSocket() {
		return datagramSocket;
	}
	public void setDatagramSocket(DatagramSocket datagramSocket) {
		this.datagramSocket = datagramSocket;
	}
	public boolean isNetwork() {
		return isNetwork;
	}
	public void setNetwork(boolean isNetwork) {
		this.isNetwork = isNetwork;
	}
	public boolean getIsRunning() {
		return isRunning;
	}
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	public void isNMEA(){
		type = IS_NMEA;
	}
	public void isSIIS(boolean isNetwork){
		type = IS_SIIS;
		this.isNetwork = isNetwork;
	}
	public void isEMM(){
		type = IS_EMM;
	}

	public boolean isSendHeading() {
		return sendHeading;
	}
	public void setSendHeading(boolean sendHeading) {
		this.sendHeading = sendHeading;
	}
	public boolean isSendOwnPosition() {
		return sendOwnPosition;
	}
	public void setSendOwnPosition(boolean sendOwnPosition) {
		this.sendOwnPosition = sendOwnPosition;
	}
	public boolean isSendTargetPosition() {
		return sendTargetPosition;
	}
	public void setSendTargetPosition(boolean sendTargetPosition) {
		this.sendTargetPosition = sendTargetPosition;
	}
	public boolean isSendTargetRange() {
		return sendTargetRange;
	}
	public void setSendTargetRange(boolean sendTargetRange) {
		this.sendTargetRange = sendTargetRange;
	}
	public boolean isSendTargetBearing() {
		return sendTargetBearing;
	}
	public void setSendTargetBearing(boolean sendTargetBearing) {
		this.sendTargetBearing = sendTargetBearing;
	}
	public boolean isSendOwnHeading() {
		return sendOwnHeading;
	}
	public void setSendOwnHeading(boolean sendOwnHeading) {
		this.sendOwnHeading = sendOwnHeading;
	}
	
	
	//###########################################################################################
	
	
	@Override
	protected Integer call() throws Exception {
		String string;
		byte[] b;
		
		//*** OPEN PORT ****
		if(isNetwork){
			datagramSocket.connect(InetAddress.getByName("213.213.213.255"), 4100);
		}else{
			serialPort.openPort();
		}
		
		//*** LOOP ****
		while (isRunning) {
			for(Sensor s: sensors){
				//OUTPUT
				switch (type) {
				case "EMM":
					string = emmString(s);
					b = string.getBytes();
					serialPort.writeBytes(b, b.length);
					break;
				case "SIIS":
					string = siisString(s);
					b = string.getBytes();
					if(isNetwork){
						//send network
						DatagramPacket p = new DatagramPacket(b, b.length);
						datagramSocket.send(p);
					}else{
						serialPort.writeBytes(b, b.length);
					}
					break;
				case "NMEA":
					string = nmeaString(s);
					b = string.getBytes();
					serialPort.writeBytes(b, b.length);
					break;
				default:
					System.out.println("No data type selected");
					
					break;
				}
			}
			
			//TODO:Wait some time
		}
		
		
		//*** CLOSE PORT ****
		if(isNetwork){
			datagramSocket.close();
		}else{
			serialPort.closePort();
		}
		
		
		// TODO Auto-generated method stub
		return null;
	}
	

	//###########################################################################################
	
	private String nmeaString(Sensor s){
		String str = "";
		if(sendHeading)str+=nmeaHDT(s);
		if(sendOwnPosition)str+=nmeaGGA(s)+nmeaGLL(s);
		return str;
	}
	private String nmeaHDT(Sensor s){
		SentenceFactory sf = SentenceFactory.getInstance();
		HDTSentence hdt = (HDTSentence) sf.createParser(TalkerId.IN, "HDT");
		hdt.setHeading(s.getOwnShip().getHeadingDeg360());
		
		return hdt.toSentence() + "\r\n" ;
	}
	private String nmeaGGA(Sensor s){
		SentenceFactory sf = SentenceFactory.getInstance();
		GGASentence gga = (GGASentence) sf.createParser(TalkerId.GP, "GGA");
		gga.setAltitude(s.getPosition().getAltitude());
		gga.setPosition(s.getPosition());
		gga.setAltitudeUnits(Units.METER);
		gga.setGeoidalHeight(0);
		gga.setSatelliteCount(9);
		gga.setHorizontalDOP(1.5);
		gga.setTime(t);
		gga.setFixQuality(GpsFixQuality.NORMAL);
		return gga.toSentence()+"\r\n";
	}
	private String nmeaGLL(Sensor s){
		SentenceFactory sf = SentenceFactory.getInstance();
		GLLSentence gll = (GLLSentence) sf.createParser(TalkerId.GP, "GLL");
		gll.setPosition(s.getPosition());
		gll.setTime(t);
		return gll.toSentence()+"\r\n";
	}
	private String siisString(Sensor s){
		String str = "";
		if (!isNetwork) str += "$SIIS,";
		str += "sensorid:" + s.getSensorID() +",";
		str += "time:" +t.getMilliseconds()/100 + ";";
		str += siisTargetRelativeBearing(s) + siisTargetRange(s);
		return str;
	}
	private String siisHeading(Sensor s){
		return "";
	}
	private String siisOwnPosition(Sensor s){
		return "latre:" + s.getPosition().getLatitude() +":deg,lonre:" + s.getPosition().getLongitude() + ":deg,";
	}
	private String siisTargetPosition(Sensor s){
		return "NOT IMPLEMENTED";
	}
	private String siisTargetRange(Sensor s){
		return "rnre:" + GeoCalculations.geoDistanceInMetersBetweenLocation(s.getPosition(), s.getTarget().getPosition()) + ":m,";
	}
	private String siisTargetRelativeBearing(Sensor s){
		return "rbre:" + GeoCalculations.geoAngleBetweenLocations(s.getPosition(), s.getTarget().getPosition()) + ":deg,";
	}
	private String siisTargetTrueBearing(Sensor s){
		return "NOT IMPLEMENTED";
	}
	private String emmString(Sensor s){
		SimpleDateFormat sdfDate = new SimpleDateFormat("hh:mm:ss");
		Date now = new Date();
		String strDate = sdfDate.format(now);		
		String string = "^000 1 "+ strDate + " " + decDegToDegMinForEMM(s.getPosition()) + " "+ "2" +" " +"10"+" "+"01"+" "+s.getPosition().getAltitude() +  " 0.000000 "; // checksum.... need to make good EMM converter
			int sum = 0;
			for (char c : string.toCharArray()){
				sum+= (int)c;
			}
		String hex = Integer.toHexString(sum);
		String checksum = hex.substring(Math.max(hex.length() - 2, 0));
		return string +=  checksum +"\r\n" ;
	}
	private static String decDegToDegMinForEMM(Position p){
		double lat = p.getLatitude();
		double lon = p.getLongitude();	
		double latDeg = Math.floor(lat);
		double lonDeg = Math.floor(lon);	
		double latMin = (lat - latDeg)*60; 
		double lonMin = (lon - lonDeg)*60;
		DecimalFormat numberFormat = new DecimalFormat("00.00000");
		NumberFormat longi = new DecimalFormat("000");
		NumberFormat lati = new DecimalFormat("00");		
		return lati.format(latDeg) + ":" + numberFormat.format(latMin) + p.getLatitudeHemisphere().toChar() + " " + longi.format(lonDeg) + ":" + numberFormat.format(lonMin) + p.getLongitudeHemisphere().toChar();
	}
	
	static String stringToHex(String string) {
		  StringBuilder buf = new StringBuilder(200);
		  for (char ch: string.toCharArray()) {
		    if (buf.length() > 0)
		      buf.append(' ');
		    buf.append(String.format("%04x", (int) ch));
		  }
		  return buf.toString();
		}
	 static long toAscii(String s){
        StringBuilder sb = new StringBuilder();
        long asciiInt;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            asciiInt = (int)c; 
            System.out.println(c +"="+asciiInt);
            sb.append(asciiInt);
        }
        return Long.parseLong(sb.toString());
	 }
	 public String toString(){
		 return name;
	 }

	
	
	
	

}
