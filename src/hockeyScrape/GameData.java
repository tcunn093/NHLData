package hockeyScrape;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class GameData {
	
	private String homeTeam, awayTeam;
	private String homeGoals, awayGoals;
	private String date;
	private int attendance;
	private String arena;
	private String startTime;
	private String endTime;
	private String gameNumber;
	private String gameStatus;
	private String url;
	private Map<Integer, Event> eventMap = new HashMap<Integer, Event>();
	
	public Map<Integer, Event> getEventMap(){
		
		return eventMap;
		
	}
	
	private Document htmlReport;
	
public static Map<Integer, Character> addOnIcePlayers(String dataLine){
		
		int playerNumber;
		char playerPosition;
		String stringPlayerNumber;
		
		Map<Integer, Character> onIceMap = new HashMap<Integer, Character>();
		//System.out.println("numwords: " + StringParsing.numberOfWords(dataLine));
		int numberOfPlayers = StringParsing.numberOfWords(dataLine) / 2;	
		//System.out.println("test");
		for (int i = 1; i <= 2*numberOfPlayers; i+=2){
			
			stringPlayerNumber = StringParsing.getNthWord(i,dataLine);
			
			//System.out.println("playernumber: " + stringPlayerNumber);
			playerNumber = Integer.parseInt(stringPlayerNumber);
			playerPosition = StringParsing.getNthWord(i+1,dataLine).charAt(0);
			//System.out.println(playerPosition);
			onIceMap.put(playerNumber, playerPosition);
			
		}
		
		return onIceMap;
			
			
		}

	public int numberOfEvents(){
		
		return eventMap.size();
		
		
	}
	
	
	public static Event addToEvent(Event e, String data, int counter){
		
		String newData;
		int numWords;
		
		switch (counter%8){
		
		case 0:
			e.setEventNumber(Integer.parseInt(data));
			break;
		case 1:
			e.setPeriod(Integer.parseInt(data));
			break;
		case 2: 
			e.setStrength(data);
			break;
		case 3:
			newData = StringParsing.getNthWord(1, data);
			e.setPeriodTime(newData);
			newData = StringParsing.getNthWord(2, data);
			e.setPeriodTimeLeft(data);
			break;
		case 4:
			e.setEvent(data);
			break;
		case 5:
			e.setDescription(data);
			break;
		case 6:
			numWords = StringParsing.numberOfWords(data);
			//Each onIce data line will return at least 6 words (ie. Strings surrounded by spaces or min/max index). If it's less,
			//then it's a non-standard line to be ignored.
			if (counter != 6 && numWords >= 6){
				e.putHomePlayersOnIce(addOnIcePlayers(data));
			}
			break;
		case 7:
			numWords = StringParsing.numberOfWords(data);
			if (counter != 7 && numWords >= 6){
				e.putAwayPlayersOnIce(addOnIcePlayers(data));
			}
			break;
		
		}
		
		return e;
		
	}
	
	public GameData(String url) throws IOException{
		
		Event event = new Event();
		
		int counter = 0;
		
		this.url = url;
		
		htmlReport = Jsoup.connect(url).maxBodySize(0).get();
		
		Elements gameInfo = htmlReport.select("table[id=GameInfo]").select("tr");
		
		String rawArena = gameInfo.get(4).text();

		String rawTime = gameInfo.get(5).text();
		
		date = gameInfo.get(3).text();
		
		attendance = Integer.parseInt(StringParsing.getNthWord(2, rawArena).replace(",", ""));
		
		arena = StringParsing.getNWords(4,6, rawArena);
	
		startTime = StringParsing.getNthWord(2, rawTime);
	
		endTime = StringParsing.getNthWord(5, rawTime);
		
		Elements dataLines = htmlReport.select("tr[class$=evenColor]");
		
		for (Element e: dataLines){
			
			Elements data = e.select("td[class$= + bborder], td[class$= + bborder + rborder] ");
			
			for (Element d: data){
				
				System.out.println(d.text());
				
				if (counter%8 == 0 && counter != 0){
					
					eventMap.put((counter)/8, event);
					
					event = null;
					event = new Event();
					
				}
				
				event = addToEvent(event, d.text(), counter);
				
				counter++;
				
			}
			
			eventMap.put((counter)/8, event);
			
			
			
		}
		
		
	
	}
	

}
