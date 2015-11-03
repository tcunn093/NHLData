package hockeyScrape;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SeasonData {
	
	private String season;
	private Map<Integer, GameData> seasonMap = new HashMap<Integer, GameData>();
	
	public static String getURL(String season, int gameNumber){
		
		return "http://www.nhl.com/scores/htmlreports/" + season + "/PL0" + Integer.toString(gameNumber) + ".HTM";
		
	}
	
	
	private void setGames() throws IOException{
		
		int badURLCounter = 3;
		int gameNumber = 20001;
		String url = getURL(season, gameNumber);
		
		GameData game;
		
		while (badURLCounter > 0){
			System.out.println("Bad URL Counter: " + badURLCounter);
			try{
				
			game = new GameData(url);
			
			} catch (Exception e){
				
				badURLCounter--;
				gameNumber++;
				url = getURL(season, gameNumber);
				continue;
				
			}
			
			addToSeasonMap(game);
			Test.printGameData(game);
			
			gameNumber++;
			
			url = getURL(season, gameNumber);
			
			badURLCounter = 3;
			
			
			
		}
		
	}
	
	
	private void addToSeasonMap(GameData g){
		
		int gameNumber = g.getGameNumber();

		seasonMap.put(gameNumber, g);
		
	}

	public SeasonData(String season) throws IOException {
		
		this.season = season;
		
		setGames();
		
	}
	
	public Map<Integer, GameData> getSeasonMap(){
		
		return seasonMap;
	}
	
	public String getSeason(){
		
	return season;	
		
	
	}

}
