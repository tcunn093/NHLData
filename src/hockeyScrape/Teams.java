package hockeyScrape;

import java.util.HashMap;
import java.util.Map;

public class Teams {
	
   public static String[] teamList = {"Toronto Maple Leafs",
											"Tampa Bay Lightning",
											"Dallas Stars",
											"Florida Panthers",
											"New York Islanders",
											"Montreal Canadiens",
											"Nashville Predators",
											"Ottawa Senators",
											"Pittsburgh Penguins",
											"Buffalo Sabres",
											"Arizona Coyotes",
											"Anaheim Ducks",
											"Detroit Red Wings",
											"New York Rangers",
											"Winnipeg Jets",
											"Columbus Blue Jackets",
											"Colorado Avalanche",
											"Carolina Hurricanes",
											"New Jersey Devils",
											"Edmonton Oilers",
											"Vancouver Canucks",
											"Calgary Flames",
											"San Jose Sharks",
											"St.Louis Blues",
											"Los Angeles Kings",
											"Boston Bruins",
											"Chicago Blackhawks",
											"Washington Capitals",
											"Philadelphia Flyers",
											"Minnesota Wild"
										  };
	
	
	public static Map<String, Integer> teamMap = new HashMap<String, Integer>();
	
	static{
		
		for (int i = 1; i <= teamList.length; i++){
			
			teamMap.put(teamList[i-1], i);
			
		}
		
		
	}
	
	public static String getFullTeamName(String partialString){
		
		String fullTeamName = "";
		String teamUpper;
		
		for (String team: teamList){
			
			teamUpper = team.toUpperCase();
			
			if (teamUpper.contains(partialString.toUpperCase())){
				fullTeamName = team;
			};
			
		}
				
		return fullTeamName;
				
	}
										  									  
}


