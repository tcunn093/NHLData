package hockeyScrape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Game {
	
	private String homeTeam, awayTeam;
	private String homeGoals, awayGoals;
	private String awayInfoLine, homeInfoLine;
	private String date;
	private int attendance;
	//private String arena;
	private String startTime;
	private String endTime;
	private int gameNumber;
	private String url;
	private Elements gameInfo;
	private Map<Integer, Event> eventMap;
	private Document htmlReport;
	private Event event;
	
	
	
	public static String generateURL(String season, int gameNumber){
		
		return "http://www.nhl.com/scores/htmlreports/" + season + "/PL0" + Integer.toString(gameNumber) + ".HTM";
		
	}
	
	public Map<Integer, Event> getEventMap(){
		
		return eventMap;
		
	}
	
	public String getHomeTeam(){
		
		return homeTeam;
		
	}
	
	public String getAwayTeam(){
		
		return awayTeam;
		
	}
	
	public String getHomeGoals(){
		
		return homeGoals;
		
	}
	
	public String getAwayGoals(){
		
		return awayGoals;
		
	}
	
	public String getDate(){
		
		return date;
	}
	
	public int getAttendance(){
		
		return attendance;
	}
	
	/**
	public String getArena(){
		
		return arena;
		
	}
	**/
	
	public String getStartTime(){
		
		return startTime;
		
	}
	
	public String getEndTime(){
		
		return endTime;
		
	}
	
	public int getGameNumber(){
		
		return gameNumber;
		
	}
	
	public String getUrl(){
		
		return url;
	}
	
	/**
	private static Map<Character, Player> onIcePlayers(String dataLine){
		
		int playerNumber;
		char playerPosition;
		String stringPlayerNumber;
		
		Map<Character, Player> onIceMap = new HashMap<Character, Player>();

		int numberOfPlayers = StringParsing.numberOfWords(dataLine) / 2;	

		for (int i = 1; i <= 2*numberOfPlayers; i+=2){
			
			stringPlayerNumber = StringParsing.getNthWord(i,dataLine);
			
			playerNumber = Integer.parseInt(stringPlayerNumber);
			playerPosition = StringParsing.getNthWord(i+1,dataLine).charAt(0);

			onIceMap.put(playerNumber, playerPosition);
			
		}
		
		return onIceMap;
			
			
		}
		**/

	public int numberOfEvents(){
		
		return eventMap.size();
		
		
	}
	
	private static void addToEvent(Event e, Element data, int counter){
		
		String newData;
		int numWords;
		Elements player;
		List<Player> playerList;
		String name, position;
		int number, c;
		String line;
		
		switch (counter%8){
		
		case 0:
			
			e.setEventNumber(Integer.parseInt(data.text()));
			
			break;
			
		case 1:
			
			e.setPeriod(Integer.parseInt(data.text()));
			
			break;
			
		case 2: 
			
			e.setStrength(data.text());
			
			break;
			
		case 3:
			
			newData = StringParsing.getNthWord(1, data.text());
			e.setPeriodTime(newData);
			
			newData = StringParsing.getNthWord(2, data.text());
			e.setPeriodTimeLeft(data.text());
			
			break;
			
		case 4:
			
			e.setEvent(data.text());
			
			break;
			
		case 5:
			
			e.setDescription(data.text());
			
			break;
			
		case 6:
			
			player = data.select("font");
			playerList = new ArrayList<Player>();
			
			c = 1;
			for (Element pl: player){
				line = pl.attr("title");
				number = Integer.parseInt(StringParsing.getNthWord(c, data.text()));
				c+=2;
				name = StringParsing.getNWords(StringParsing.numberOfWords(line)-1, StringParsing.numberOfWords(line), line);
				position = StringParsing.getNWords(1, StringParsing.getWordNumber("-", line) - 1, line);
				//System.out.printf("Player: %s\n Number: %d\n Position: %s\n", name, number, position);
				playerList.add(new Player(name, number, position));
			}
			
			
			e.setHomePlayersOnIce(playerList);
			break;
			
			
		case 7:
			player = data.select("font");
			playerList = new ArrayList<Player>();
			
			c = 1;
			for (Element pl: player){
				line = pl.attr("title");
				number = Integer.parseInt(StringParsing.getNthWord(c, data.text()));
				c+=2;
				name = StringParsing.getNWords(StringParsing.numberOfWords(line)-1, StringParsing.numberOfWords(line), line);
				position = StringParsing.getNWords(1, StringParsing.getWordNumber("-", line) - 1, line);
				//System.out.printf("Player: %s\n Number: %d\n Position: %s\n", name, number, position);
				playerList.add(new Player(name, number, position));
			}
			
			e.setAwayPlayersOnIce(playerList);
			break;
		
		}
		
		
	}
	
	private void setAwayGoals(){

		awayGoals = StringParsing.getNthWord(2, awayInfoLine);
		
	}
	
	private void setAwayTeam(){
		
		String awayPartial = StringParsing.getNthWord(-6, awayInfoLine);
		awayTeam = Teams.getFullTeamName(awayPartial);
		
	}
	
	private void setHomeTeam(){
		
		String homePartial = StringParsing.getNthWord(-6, homeInfoLine);
		homeTeam = Teams.getFullTeamName(homePartial);
		
	}

	private void setHomeGoals(){
		
		homeGoals = StringParsing.getNthWord(2, homeInfoLine);
		
	}
	
	private void setGameNumber(){
		
		String rawGameNumber = gameInfo.get(6).text();
		gameNumber = Integer.parseInt(StringParsing.getNthWord(-1, rawGameNumber));
		
	}
	
	private void setAwayInfoLine(){
		
		awayInfoLine =  htmlReport.select("table[id=Visitor]").first().text();
		
	}
	
	private void setHomeInfoLine(){
		
		homeInfoLine =  htmlReport.select("table[id=Home]").first().text();
	}
	
	private void setHTMLDocument() throws IOException{
		
		htmlReport = Jsoup.connect(url).maxBodySize(0).get();
		
	}
	
	private void setGameInfo(){
		
		gameInfo = htmlReport.select("table[id=GameInfo]").select("tr");
		
	}
	/**
	private void setArena(){
		
		String rawArena = gameInfo.get(4).text();
		arena = StringParsing.getNWords(4,6, rawArena);
		
	}
	**/
	
	private void setAttendance(){
		
		String rawAttendance = gameInfo.get(4).text();
		attendance = Integer.parseInt(StringParsing.getNthWord(2, rawAttendance).replace(",", ""));
		
	}
	
	private void setDate(){
		
		date = gameInfo.get(3).text();
		
	}
	
	private void setStartEndTimes(){
		
		String rawTime = gameInfo.get(5).text();
		
		startTime = StringParsing.getNthWord(2, rawTime);
	
		endTime = StringParsing.getNthWord(-2, rawTime);
		
	}
	
	
	
	private void setEvents(){
		
		event = new Event();
		
		int counter = 0;

		Elements dataLines = htmlReport.select("tr[class$=evenColor]");
		
		for (Element e: dataLines){
			
			Elements data = e.select("td[class$= + bborder], td[class$= + bborder + rborder]");
			
			Elements homePlayers = e.select("td[class$= + bborder + rborder]");
			
			Elements awayPlayers = e.select("td[class$= + bborder + rborder] + td[class$= + bborder]");
			
			Elements player;
			
			for (Element d: data){
				
				//Save event to Map on counter reset (multiple of 8)
				if (counter%8 == 0 && counter != 0){
					
					eventMap.put((counter)/8, event);
					
					event = new Event();
					
				}
			
				//Add a data entry to the event
				addToEvent(event, d, counter);
				
				
				counter++;
				
			}
			
			
		
			
			
			//eventMap.put((counter)/8, event);	
			
		}
		
	}
	
	public Game(String url) throws IOException{
		
		this.url = url;
		eventMap = new HashMap<Integer, Event>();
		
		setHTMLDocument();//Must execute before all other methods.
		
		//----------------
		
		setAwayInfoLine();//Must execute before all other methods up to line below.
		setHomeInfoLine();//Must execute before all other methods up to line below.
				
		setHomeTeam();
		setHomeGoals();
		
		setAwayTeam();
		setAwayGoals();
		
		//----------------

		setGameInfo();//Must execute before all other methods up to line below.
		
		//setArena();
		setAttendance();
		setStartEndTimes();
		setDate();
		setGameNumber();
		
		//----------------
		
		setEvents();
		
	}
	

}
