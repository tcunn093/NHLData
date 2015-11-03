package hockeyScrape;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;

public class NHLData {
	
	private static String initialSeason = "20072008";
	private Map<String, SeasonData> NHLMap = new HashMap<String, SeasonData>();
	
	private void addToNHLMap(SeasonData s){
		
		NHLMap.put(s.getSeason(), s);
		
	}
	
	
	public Map<String, SeasonData> getNHLMap(){
		
		return NHLMap;
		
	}
	private void setSeasons() throws IOException{
		
		String currentSeason = initialSeason;
		
		SeasonData season;
		
		while (validSeason(currentSeason) == true){
			
			season = new SeasonData(currentSeason);
			
			addToNHLMap(season);
			
			currentSeason = nextSeason(currentSeason);
			
			
		}
		
	}
	
	

	public NHLData() throws IOException {

		setSeasons();
		
		
	}
	
	private static String nextSeason(String currentSeason){
		
		int intCurrentSeason = Integer.parseInt(currentSeason);
		int intNextSeason = intCurrentSeason + 10001;
		
		return Integer.toString(intNextSeason);
		
		
	}
	
	private boolean validSeason(String currentSeason){
		
		String url = SeasonData.getURL(currentSeason, 20001);
		
		try{
			
			Jsoup.connect(url).get();
			
			} catch (Exception e){
				
				return false;
				
			}
		
		return true;
	}
	
	

}
