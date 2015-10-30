package hockeyScrape;

public class Teams {
	
	private static final String[] teams = {"TORONTO MAPLE LEAFS",
										  "MONTREAL CANADIENS",
										  "LOS ANGELES KINGS",
										  "MINNESOTA WILD",
										  "COLUMBUS BLUE JACKETS",
										  "BUFFALO SABRES",
										  "SAN JOSE SHARKS",
										  "ANAHEIM DUCKS",
										  "VANCOUVER CANUCKS",
										  "CALGARY FLAMES",
										  "COLORADO AVALANCHE",
										  "ARIZONA COYOTES",
										  "DALLAS STARS",
										  "WINNIPEG JETS",
										  "EDMONTON OILERS",
										  "NEW YORK ISLANDERS",
										  "NEW YORK RANGERS",
										  "NEw JERSEY DEVILS",
										  "FLORIDA PANTHERS",
										  "CAROLINA HURRICANES",
										  "OTTAWA SENATORS",
										  "BOSTON BRUINS",
										  "CHICAGO BLACKHAWKS",
										  "NASHVILLE PREDATORS",
										  "ST.LOUIS BLUES",
										  "PITTSBURGH PENGUINS",
										  "PHILADELPHIA FLYERS",
										  "DETROIT RED WINGS",
										  "TAMPA BAY LIGHTNING",
										  "WASHINGTON CAPITALS"
										  };
	
	public static String getFullTeamName(String partialString){
		
		String fullTeamName = "";
		
		for (String team: teams){
			
			if (team.contains(partialString)){
				fullTeamName = team;
			};
			
		}
				
			
		return fullTeamName;
				
	}
										  
										  
}


