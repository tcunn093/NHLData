package hockeyScrape;

import java.sql.SQLException;
import javasql.ExportToSQLDatabase;

public class SQLExportTEST {

	public static void main(String[] args) throws SQLException {
		
		ExportToSQLDatabase.setSchema("hockey");
		
		ExportToSQLDatabase.connect();
		
		System.out.println("Success");
		
		ExportToSQLDatabase.executeDDL(
				
				"REPLACE INTO Teams_T(Team_Name, Team_Arena)" + "VALUES ('TORONTO MAPLE LEAFS', 'Air Canada Centre');"
				
				);


		ExportToSQLDatabase.disconnect();
		

	}

}
