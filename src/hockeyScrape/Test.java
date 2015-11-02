package hockeyScrape;

import java.io.IOException;
import java.util.Map;


public class Test {
	
	
	public static void printMap(Map<Integer, Character> mapToPrint){
		
		for (Map.Entry<Integer, Character> entry: mapToPrint.entrySet()){
			System.out.printf("\n%s: %s\n", entry.getKey(), entry.getValue());
		}
		
	}
	

	public static void main(String[] args) throws IOException {
		
		double startTime = System.currentTimeMillis();
		
		GameData g = new GameData("http://www.nhl.com/scores/htmlreports/20152016/PL020001.HTM");
		
		Map<Integer, Event> eMap = g.getEventMap();
		
		System.out.println("Number of Events: " + g.numberOfEvents());
		
		printMap(eMap.get(326).getAwayPlayersOnIce());
		
		double endTime = System.currentTimeMillis();

		double duration = (endTime - startTime);
		
		System.out.printf("\nTook %.3f seconds", duration/1000);
		
		
	}

}
