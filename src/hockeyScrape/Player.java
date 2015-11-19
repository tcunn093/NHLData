package hockeyScrape;

public class Player {
	
	private int playerNumber;
	private String playerName;
	private String playerPosition;

	public Player() {
		
		playerNumber = 0;
		playerName = "";
		playerPosition = "";
		
	}
	
	public Player(String playerName, int playerNumber, String playerPosition) {
		
		setPlayerNumber(playerNumber);
		setPlayerName(playerName);
		setPlayerPosition(playerPosition);
		
		
	}
	
	public String getPlayerPosition() {
		return playerPosition;
	}

	public void setPlayerPosition(String playerPosition) {
		this.playerPosition = playerPosition;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


}
