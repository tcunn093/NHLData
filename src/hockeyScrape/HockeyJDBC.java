package hockeyScrape;

import javasql.SQLDatabase;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HockeyJDBC {
	
	private static final int badURLLimit = 3;
	private static final int initialGameNumber = 20001;

	public HockeyJDBC() throws SQLException {
		
		SQLDatabase.setSchema("hockey");
		SQLDatabase.connect();
		
	}
	
	public void addSeason(String season) throws IOException, SQLException{
		
		int badURLCounter = badURLLimit;
		int gameNumber = initialGameNumber;
		String url = Game.generateURL(season, gameNumber);
		
		System.out.println(url);
		
		Game game = null;

		while (badURLCounter > 0){
			
			System.out.println("Bad URL Counter: " + badURLCounter);
			
			try{

			game = new Game(url);
			
			HockeyJDBC.addGamesToTable(game);
			
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
	
	public void addToTeamsTable(Game g) throws SQLException{
		
		String arena = g.getArena();
		String teamName = g.getHomeTeam();
		
		String sql = "REPLACE INTO Teams_T(Team_Name, Team_Arena)" + "VALUES ('" + teamName + "', '" + arena + "');";
		
		SQLDatabase.executeDDL(sql);
		
	}
	
	public static void addGamesToTable(Game... gameArray) throws SQLException{
		
		String homeTeam, awayTeam, startTime, endTime, url, date;
		int attendance, gameNumber, homeGoals, awayGoals;

		for (Game g: gameArray){
			
			gameNumber = g.getGameNumber();
			homeTeam = g.getHomeTeam();
			awayTeam = g.getAwayTeam();
			startTime = formatTime(g.getStartTime());
			endTime = formatTime(g.getEndTime());
			url = g.getUrl();
			date = formatDate(g.getDate());
			attendance = g.getAttendance();
			homeGoals = Integer.parseInt(g.getHomeGoals());
			awayGoals = Integer.parseInt(g.getAwayGoals());
			
			String GameAdd = "REPLACE INTO Games_T(Home_Team, Home_Goals, Away_Team, Away_Goals, Start_Time, End_Time, Game_URL, Game_Attendance, Game_Number, Game_Date)" + 
			"VALUES ('" + homeTeam + "', '" + homeGoals + "', '" + awayTeam + "', '" + awayGoals + "', '" + startTime + "', '" + endTime + "', '" + url + "', '" + attendance + "', '" + gameNumber + "', '" + date +"');";
			
			SQLDatabase.executeDDL(GameAdd);
		
		}
		
	}
	
	
	
	

}
