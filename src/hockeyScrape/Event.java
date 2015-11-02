package hockeyScrape;

import java.util.HashMap;
import java.util.Map;

public class Event {
	
	private int eventNumber;
	private int period;
	private String strength;
	private String periodTime;
	private String periodTimeLeft;
	private String event;
	private String description;
	private Map<Integer, Character> homePlayersOnIce;
	private Map<Integer, Character> awayPlayersOnIce;
	
	public Event(){
		
		eventNumber = 0;
		period = 0;
		strength = "";
		periodTime = "";
		periodTimeLeft = "";
		event = "";
		description = "";
		homePlayersOnIce = new HashMap<Integer, Character>();
		awayPlayersOnIce = new HashMap<Integer, Character>();
		
	}
	
	public void putHomePlayersOnIce(Map<Integer, Character> onIceMap){
		
		homePlayersOnIce = onIceMap;
		
	}
	
	public void putAwayPlayersOnIce(Map<Integer, Character> onIceMap){
		
		awayPlayersOnIce = onIceMap;
		
	}
	
	public Map<Integer, Character> getHomePlayersOnIce(){
		
		return homePlayersOnIce;
		
	}
	
	public Map<Integer, Character> getAwayPlayersOnIce(){
		
		return awayPlayersOnIce;
		
	}
	
	public void setEventNumber(int eventNumber){
		
		this.eventNumber = eventNumber;
		
	}
	
	public int getEventNumber(){
		
		return eventNumber;
		
	}
	
	public void setPeriod(int period){
		
		this.period = period;
		
	}
	
	public int getPeriod(){
		
		return period;
		
	}
	
	public void setStrength(String strength){
		
		this.strength = strength;
		
	}
	
	public String getStrength(){
		
		return strength;
		
	}
	
	public void setPeriodTime(String periodTime){
		
		this.periodTime = periodTime;
		
	}
	
	public String getPeriodTime(){
		
		return periodTime;
		
	}
	
	public void setPeriodTimeLeft(String periodTimeLeft){
		
		this.periodTimeLeft = periodTimeLeft;
		
	}
	
	public String getPeriodTimeLeft(){
		
		return periodTimeLeft;
		
	}
	
	public void setEvent(String event){
		
		this.event = event;
		
	}
	
	public String getEvent(){
		
		return event;
		
	}
	
	public void setDescription(String description){
		
		this.description = description;
		
	}
	
	public String getDescription(){
		
		return description;
		
	}

}
