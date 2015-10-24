package hockeyScrape;

public class StringParsing {
	
public static int numberOfWords(String input){
	
	boolean lastCharWasNotEmpty = true;
	char[] charArray = input.toCharArray();
	int numberOfWords = 0;
	
	for (char c: charArray){
		//Hello, my name is jack
		if (isSpace(c) == true && lastCharWasNotEmpty == true){
			
			numberOfWords++;
			lastCharWasNotEmpty = false;
			
			System.out.print(numberOfWords);
				
			}
		
		if (isSpace(c) == false){
			
			System.out.print(c);
			lastCharWasNotEmpty = true;

		}
	
		}
	
	if (input.length() != 0){
		numberOfWords++;
	}
		
	
	
	
	return numberOfWords;
}
	

	

	
public static boolean isSpace(char c){
	
	return (int) c == 160 || (int) c == 32;
	
}

public static String getNthWord(int n, String input){
		
		int counter = 0, i = 0;
		
		char[] charArray = input.toCharArray();
		
		String word = "";
		boolean isSpace;
		
		while (counter < n && i < charArray.length){
			
			isSpace = isSpace(charArray[i]);

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
