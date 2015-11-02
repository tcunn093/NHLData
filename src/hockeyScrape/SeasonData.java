package hockeyScrape;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SeasonData {
	
	private String currentSeason;
	private Map<Integer, GameData> seasonMap = new HashMap<Integer, GameData>();
	
	private String getURL(String season, int gameNumber){
		
		return "http://www.nhl.com/scores/htmlreports/" + season + "/PL0" + Integer.toString(gameNumber) + ".HTM";
		
	}
	
	private void setGames() throws IOException{
		
		int badURLCounter = 3;
		int gameNumber = 20001;
		String url = getURL(currentSeason, gameNumber);

		GameData game;
		
		while (badURLCounter > 0){
					
			try{
				
			game = new GameData(url);
			
			} catch (Exception e){
				
				badURLCounter--;
				continue;
				
			}
			
			addToSeasonMap(game);
			Test.printGameData(game);
			
			gameNumber++;
			
			url = getURL(currentSeason, gameNumber);
			
			badURLCounter = 3;
			
			
			
		}
		
	}
	
	
	private void addToSeasonMap(GameData g){
		
		int gameNumber = g.getGameNumber();

		seasonMap.put(gameNumber, g);
		
	}

	public SeasonData(String currentSeason) throws IOException {
		
		this.currentSeason = currentSeason;
		
		setGames();
		
	}
	
	public Map<Integer, GameData> getSeasonMap(){
		
		
		return seasonMap;
	}

}
