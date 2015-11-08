package hockeyScrape;

	public class StringParsing {
		
	public static int numberOfWords(String input){
		
		boolean lastCharWasNotEmpty = true;
		char[] charArray = input.toCharArray();
		int numberOfWords = 0;
		
		for (char c: charArray){
	
			if (isSpace(c) == true && lastCharWasNotEmpty == true){
				
				numberOfWords++;
				lastCharWasNotEmpty = false;
			
					
				}
			
			if (isSpace(c) == false){
				
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
			
			int counter = 0;
			
			int i = 0;
			
			char[] charArray = input.toCharArray();
			
			String word = "";
			boolean isSpace;
			
			
		    if (n >= 0){
		    	
				while (counter < n && i < charArray.length){
					
					isSpace = isSpace(charArray[i]);
		
					if (counter == (n - 1) && isSpace == false){ 
						
						word += charArray[i];
						
					}
					
					if (isSpace == true && !isSpace(charArray[i-1])){
						
						counter++;
						
					}
					
					i++;
				}
		    } else {
		    	
		    	n *= -1;
		    	
		    	while (counter >= 0 && i < charArray.length){
		    		
					isSpace = isSpace(charArray[charArray.length - (i+1)]);
		
					if (counter == (n - 1) && isSpace == false){ 
						
						word = charArray[charArray.length - (i+1)] + word;
						
					}
					
					if (isSpace == true && !isSpace(charArray[charArray.length - (i + 2)])){
						
						counter++;
						
					}
					
					i++;
				}
		    	
		    	
		    }
	
			return word;
			
		}
		
	public static String getNWords(int startWordNumber, int endWordNumber, String input){
		
	String wordString = "";
	
	for(int i = startWordNumber; i <=endWordNumber; i++){
		
		wordString += getNthWord(i, input);
		
			if (i != endWordNumber){
				wordString += " ";
			}
		
	}
	
	return wordString;

}
	
}
