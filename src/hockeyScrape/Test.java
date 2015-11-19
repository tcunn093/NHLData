package hockeyScrape;


import java.io.IOException;
import java.sql.SQLException;

import java.util.Map;


public class Test {
	
	public static void printGame(Game g){
		
		System.out.printf("\nGame Number: %s"
				+ "\nHome Team: %s"
				+ "\nHome Goals: %s"
				+ "\nAway Team: %s"
				+ "\nAway Goals: %s"
				+ "\nDate: %s"
				//+ "\nArena: %s"
				+ "\nStart Time: %s"
				+ "\nEnd Time: %s"
				+ "\nAttendance: %d"
				+ "\nURL: %s\n", 		  g.getGameNumber(),
									  g.getHomeTeam(),
									  g.getHomeGoals(),
									  g.getAwayTeam(),
									  g.getAwayGoals(),
									  g.getDate().toString(),
									  //g.getArena(),
									  g.getStartTime(),
									  g.getEndTime(),
									  g.getAttendance(),
									  g.getUrl()); 
										
				
								
				
							
		
	}
	
	public static void printSMap(Map<Integer, Game> mapToPrint){
		
		for (Map.Entry<Integer, Game> entry: mapToPrint.entrySet()){
			System.out.printf("\n%s: %s\n", entry.getKey(), entry.getValue());
		}
		
	}
	
public static void printGMap(Map<Integer, Event> mapToPrint){
		
		for (Map.Entry<Integer,Event> entry: mapToPrint.entrySet()){
			System.out.printf("\n%s: %s\n", entry.getKey(), entry.getValue());
		}
		
	}
	

	public static void main(String[] args) throws IOException, SQLException {
		
		double startTime = System.currentTimeMillis();

		//System.out.println(HockeyJDBC.formatTime("7:11"));
		
		Game g = new Game("http://www.nhl.com/scores/htmlreports/20152016/PL020008.HTM");
		/**
		for(Map.Entry<Integer, Event> me: g.getEventMap().entrySet()){
			
			System.out.printf("\n Key: %s, Value: %s\n", me.getKey(), me.getValue().getEvent());
			
		}
		
		g.getEventMap().entrySet();
		**/
		//HockeyJDBC h = new HockeyJDBC();
		//h.addSeason("20152016");
		//h.1printStatements();
		//h.exit();
		//printGame(g);
		
		//System.out.println(g.getHomeGoals());
		//System.out.println(ng.getHomeGoals());
		
		
		double endTime = System.currentTimeMillis();

		double duration = (endTime - startTime);
		
		System.out.printf("\nTook %.3f seconds", duration/1000);
		
		
	}

}
