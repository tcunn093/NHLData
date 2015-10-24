package hockeyScrape;

public class StringParsing {

public static String getNthWord(int n, String input){
		
		int counter = 0, i = 0;
		
		char[] charArray = input.toCharArray();
		
		String word = "";
		boolean isSpace;
		
		while (counter < n && i < charArray.length){
			
			isSpace = (int) charArray[i] == 160 || (int) charArray[i] == 32;

			if (counter == (n - 1) && isSpace == false){ 
				
				word += charArray[i];
				
			}
			
			if (isSpace == true){
				
				counter++;
				
			}
			
			i++;
		}
		
		return word;
		
	}
	
	public static String getNWords(int startWordNumber, int endWordNumber, String input){
		
		String wordString = "";
		
		for(int i = startWordNumber; i <=endWordNumber; i++){
			
			wordString += getNthWord(i, input);
			
		}
		
		return wordString;
	}
	
}
