package hockeyScrape;

public class Teams {
	
	public static final String[] teamList = {"TORONTO MAPLE LEAFS",
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
										  "NEW JERSEY DEVILS",
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
										  "WASHINGTON CAPITALS",
										  "ATLANTA THRASHERS"
										  };
	
	public static String getFullTeamName(String partialString){
		
		String fullTeamName = "";
		
		for (String team: teamList){
			
			if (team.contains(partialString)){
				fullTeamName = team;
			};
			
		}
				
		return fullTeamName;
				
	}
										  									  
}


