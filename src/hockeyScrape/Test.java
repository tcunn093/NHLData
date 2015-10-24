package hockeyScrape;

import java.io.IOException;
import java.util.Map;

public class Test {

	public static void main(String[] args) throws IOException {
		
		GameData g = new GameData("http://www.nhl.com/scores/htmlreports/20152016/PL020001.HTM");
		
		Map<Integer, Event> eMap = g.getEventMap();
		
		System.out.print(eMap.get(327).getDescription());
	}

}
