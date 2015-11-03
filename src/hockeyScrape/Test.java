package hockeyScrape;

import java.io.IOException;
import java.util.Map;


public class Test {
	
	public static void printGameData(GameData g){
		
		System.out.printf("\nGame Number: %s"
				+ "\nHome Team: %s"
				+ "\nHome Goals: %s"
				+ "\nAway Team: %s"
				+ "\nAway Goals: %s"
				+ "\nDate: %s"
				+ "\nArena: %s"
				+ "\nStart Time: %s"
				+ "\nEnd Time: %s"
				+ "\nAttendance: %d"
				+ "\nURL: %s\n", 		  g.getGameNumber(),
									  g.getHomeTeam(),
									  g.getHomeGoals(),
									  g.getAwayTeam(),
									  g.getAwayGoals(),
									  g.getDate(),
									  g.getArena(),
									  g.getStartTime(),
									  g.getEndTime(),
									  g.getAttendance(),
									  g.getUrl()); 
										
				
								
				
							
		
	}
	
	public static void printSMap(Map<Integer, GameData> mapToPrint){
		
		for (Map.Entry<Integer, GameData> entry: mapToPrint.entrySet()){
			System.out.printf("\n%s: %s\n", entry.getKey(), entry.getValue());
		}
		
	}
	
public static void printGMap(Map<Integer, Event> mapToPrint){
		
		for (Map.Entry<Integer,Event> entry: mapToPrint.entrySet()){
			System.out.printf("\n%s: %s\n", entry.getKey(), entry.getValue());
		}
		
	}
	

	public static void main(String[] args) throws IOException {
		
		double startTime = System.currentTimeMillis();
		
		//GameData g = new GameData("http://www.nhl.com/scores/htmlreports/20152016/PL020050.HTM");
		
		NHLData n = new NHLData();
		
		Map<String, SeasonData> NMap = n.getNHLMap();
		
		//System.out.println("Number of Events: " + g.numberOfEvents());
		
		//printGameData(g);

		System.out.println(NMap.size());
		
		
		
		double endTime = System.currentTimeMillis();

		double duration = (endTime - startTime);
		
		System.out.printf("\nTook %.3f seconds", duration/1000);
		
		
	}

}
