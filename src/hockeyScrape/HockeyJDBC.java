package hockeyScrape;

import javasql.SQLDatabase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class HockeyJDBC {
	
	private static final int badURLLimit = 3;
	private static final int initialGameNumber = 20001;
	private static List<String> eventSqlStatements;
	private static List<String> gameSqlStatements;
	private static List<String> sqlStatements;

	public HockeyJDBC() throws SQLException {
		
		SQLDatabase.setSchema("hockey");
		SQLDatabase.connect();
		
		sqlStatements = new ArrayList<String>();
		
		eventSqlStatements = new ArrayList<String>();

		gameSqlStatements = new ArrayList<String>();
		
	}
	
	public void addSeason(String season) throws IOException, SQLException{
		
		int badURLCounter = badURLLimit;
		int gameNumber = initialGameNumber;
		String url = Game.generateURL(season, gameNumber);
		
		System.out.println(url);
		
		Game game = null;
		System.out.println(gameNumber);
		while ((badURLCounter > 0 ) && gameNumber < 20020){
			
			System.out.println("Bad URL Counter: " + badURLCounter);
			
			try{
				
			
			game = new Game(url);
			
			addEventsToTable(game);
			addGamesToTable(game);
			
			//addToTeamsTable(game);
			
			} catch (IOException e){
				
				badURLCounter--;
				gameNumber++;
				
				url = Game.generateURL(season, gameNumber);
				
				continue;
				
			} catch (SQLException se){
				
				System.out.println(se);
				
			}
		
			Test.printGame(game);
			gameNumber++;
			
			url = Game.generateURL(season, gameNumber);
			
			badURLCounter = badURLLimit;
			
			game = null;
			
			
		}
		
	}
	
	public static String formatDate(String s){
		
		Date date = new Date();
		String rawpattern = "E, MMMM d, yyyy";
		String formatPattern = "yyyy'-'MM'-'dd";
		SimpleDateFormat raw = new SimpleDateFormat(rawpattern);
		SimpleDateFormat refined = new SimpleDateFormat(formatPattern);
		try {
			date = raw.parse(s);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return refined.format(date);
		
		
	}
	
	
	public void printStatements() throws FileNotFoundException{
		
		System.out.println();
		
		PrintWriter out = new PrintWriter("C:\\Users\\Thomas\\Workspace\\hockeyScrape\\SQL.txt");
		
		sqlStatements.addAll(gameSqlStatements);
		sqlStatements.addAll(eventSqlStatements);
		
		
		for (String sql: sqlStatements){
			
			out.println(sql);
			
		}
		
		out.close();
		
	}
	
	public static String formatTime(String t){
		
		
		Date date = new Date();
		String rawpattern = "h:mm";
		String formatPattern = "hh':'mm':'ss";
		
		SimpleDateFormat raw = new SimpleDateFormat(rawpattern);
		SimpleDateFormat refined = new SimpleDateFormat(formatPattern);
		try {
			date = raw.parse(t);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return refined.format(date);
			
		
	}
	public void exit() throws SQLException{
		
		SQLDatabase.disconnect();
		
	}
	/**
	public void addToTeamsTable(Game g) throws SQLException{
		
		//String arena = g.getArena();
		String teamName = g.getHomeTeam();
		
		String sql = "REPLACE INTO Team_T(Team_Name)" + "VALUES ('" + teamName + "');";
		
		String insert = "INSERT INTO Team_T(Team_Name)" + "VALUES ('" + teamName + "');";
		
		teamSqlStatements.add(insert);
		SQLDatabase.executeDDL(sql);
		
	}
	**/
	private void addGamesToTable(Game... gameArray) throws SQLException{
		
		String startTime, endTime, url, date;
		int attendance, gameNumber, homeGoals, awayGoals, homeID, awayID;
		Map<String, Integer> teams = Teams.teamMap;

		
		for (Game g: gameArray){
			
			gameNumber = g.getGameNumber();
			homeID = teams.get(g.getHomeTeam());
			awayID = teams.get(g.getAwayTeam());
			startTime = formatTime(g.getStartTime());
			endTime = formatTime(g.getEndTime());
			url = g.getUrl();
			date = formatDate(g.getDate());
			attendance = g.getAttendance();
			homeGoals = Integer.parseInt(g.getHomeGoals());
			awayGoals = Integer.parseInt(g.getAwayGoals());
			
			
			String GameAdd = "REPLACE INTO Game_T(Home_Team_ID, Home_Goals, Away_Team_ID, Away_Goals, Start_Time, End_Time, Game_URL, Game_Attendance, Game_Number, Game_Date)" + 
			"VALUES ('" + homeID + "', '" + homeGoals + "', '" + awayID + "', '" + awayGoals + "', '" + startTime + "', '" + endTime + "', '" + url + "', '" + attendance + "', '" + gameNumber + "', '" + date +"');";
			
			String insert = "INSERT INTO Game_T(Home_Team_ID, Home_Goals, Away_Team_ID, Away_Goals, Start_Time, End_Time, Game_URL, Game_Attendance, Game_Number, Game_Date)" + 
					"VALUES ('" + homeID + "', '" + homeGoals + "', '" + awayID + "', '" + awayGoals + "', '" + startTime + "', '" + endTime + "', '" + url + "', '" + attendance + "', '" + gameNumber + "', '" + date +"');";
			
			gameSqlStatements.add(insert);
			SQLDatabase.executeDDL(GameAdd);
		
		}
		
		
	}
	
	private void addEventsToTable(Game... gameArray) throws SQLException{
		
		int gameNumber;
		int eventNumber;
		int period;
		String strength;
		String periodTime;
		//String periodTimeLeft;
		String event;
		//String description;
		//Map<Integer, Character> homePlayersOnIce;
		//Map<Integer, Character> awayPlayersOnIce;
		Map<Integer, Event> eventMap;
		
		String insert;
		String strengthFix;
		
		Event e;
		
		for (Game g: gameArray){
			
			eventMap = g.getEventMap();
			
			gameNumber = g.getGameNumber();
			
			for (Map.Entry<Integer, Event> eventObj : eventMap.entrySet()){

				e = eventObj.getValue();

				eventNumber = e.getEventNumber();
				period = e.getPeriod();
				
				if (e.getStrength().length() > 1){
					
					strengthFix = "'" + e.getStrength() + "'";
					
				} else{
					strengthFix = null;
				}
				
				
				

				periodTime = e.getPeriodTime();
				event = e.getEvent();
				//description = e.getDescription();
				
				
				String eventAdd = "REPLACE INTO Event_T(Game_ID, Event_ID, Event_Type_Abbr, Period, Strength_Abbr, Time_Elapsed)" +
								"VALUES ('" + gameNumber + "', '" + eventNumber + "', '" + event + "', '" + period + "', " + strengthFix + ", '" + periodTime +"');";

				insert = "INSERT INTO Event_T(Game_ID, Event_ID, Event_Type_Abbr, Period, Strength_Abbr, Time_Elapsed)" +
						"VALUES ('" + gameNumber + "', '" + eventNumber + "', '" + event + "', '" + period + "', " + strengthFix + ", '" + periodTime +"');";
		
				eventSqlStatements.add(insert);

				//SQLDatabase.executeDDL(eventAdd);
			
			}
	
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	
	

}
