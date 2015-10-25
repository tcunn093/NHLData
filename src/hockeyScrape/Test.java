package hockeyScrape;

import java.io.IOException;
import java.util.Map;


public class Test {
	
	
	public static <T> void printMap(Map<Integer, Character> mapToPrint){
		
		for (Map.Entry<Integer, Character> entry: mapToPrint.entrySet()){
			System.out.printf("%s: %s", entry.getKey(), entry.getValue());
		}
		
	}
	


	public static void main(String[] args) throws IOException {
		
		GameData g = new GameData("http://www.nhl.com/scores/htmlreports/20152016/PL020001.HTM");
		
		Map<Integer, Event> eMap = g.getEventMap();
		
		
		
		printMap(eMap.get(324).getHomePlayersOnIce());
		
		//System.out.println(StringParsing.numberOfWords("Hello, my name is Jack"));
	}

}
